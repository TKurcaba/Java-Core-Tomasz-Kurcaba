import org.apache.spark.sql.{DataFrame, Dataset, Row}
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.functions.{col, collect_set, count, desc, udf}

import java.util

object Transformation {
  private val tempUdf: (Int, Int, Int) => Int = (user_id:Int, subscriber_id: Int, owner_id:Int) => {
    if(user_id == owner_id) subscriber_id
    else owner_id
  }
  val returnSubscriber: UserDefinedFunction = udf(tempUdf)

  private def subscriber(retweetDf: DataFrame,messageDf: DataFrame): collection.Map[Int, util.List[Nothing]] = {

    retweetDf.join(messageDf.select("USER_ID","MESSAGE_ID").withColumnRenamed("USER_ID","OWNER_ID")
      ,Seq("MESSAGE_ID")).withColumn("include",returnSubscriber(col("USER_ID"),col("SUBSCRIBER_ID")
      ,col("OWNER_ID"))).groupBy("OWNER_ID").agg(collect_set("include"))
      .rdd.map(row => row.getInt(0) -> row.getList(1)).collectAsMap()
  }

  def filterFirstSecondWaves(retweetDf: DataFrame,messageDf: DataFrame ):DataFrame = {
    val subscribers = this.subscriber(retweetDf, messageDf)
    retweetDf.filter(Row => subscribers.getOrElse(Row.getInt(0),-1) != -1 || Row.getInt(0) == Row.getInt(2))
      .groupBy("MESSAGE_ID").agg(count("MESSAGE_ID").as("NUMBER_RETWEETS"))
  }

  def joinAndPrepareToPrint(result: DataFrame, messageDf: DataFrame, message_dirDf: DataFrame, user_dirDf:DataFrame): Dataset[Row] = {
    val toPrint = result.join(messageDf, Seq("MESSAGE_ID")).join(message_dirDf, Seq("MESSAGE_ID"))
      .join(user_dirDf, Seq("USER_ID"))
    toPrint.select("USER_ID", "FIRST_NAME", "LAST_NAME", "MESSAGE_ID","TEXT","NUMBER_RETWEETS")
      .orderBy(desc("NUMBER_RETWEETS"))
  }
}
