import org.apache.hadoop.shaded.org.eclipse.jetty.websocket.common.frames.DataFrame
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite

class Tests extends AnyFunSuite with BeforeAndAfterEach {


    test("Testing application"){
      val mainApp = main
      val args = Array[String]("message.avro","user_dir.avro", "message_dir.avro", "retweet.avro")
      mainApp.main(args)
    }
  test("Testing filterFirstSecondWaves and joinAndPrepareToPrint functions."){
    val dataReader= DataReader
    val messageDf = dataReader.readAvro("message.avro")
    val retweetDf = dataReader.readAvro("retweet.avro")
    val transformation = Transformation
    val user_dirDf = dataReader.readAvro("user_dir.avro")
    val message_dirDf= dataReader.readAvro("message_dir.avro")


    val result = transformation.filterFirstSecondWaves(retweetDf, messageDf)
    assert(result.count() == 4)


    val result2 = transformation.joinAndPrepareToPrint(result,messageDf,message_dirDf ,user_dirDf ).first().getLong(5)
    assert(result2== 5)
  }

}
