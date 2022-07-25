import org.apache.spark.sql.DataFrame

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._

object Calculations {
    def getCurrentAmount( df :DataFrame) : Unit = {
      val windowDept = Window.partitionBy("positionID","warehouse","product").orderBy(col("eventTime").desc)
      df.withColumn("row",row_number.over(windowDept )).where(col("row" )=== 1).drop("row","eventTime")
        .show()
    }
    def describeWarehouse(df : DataFrame): Unit = {
      df.groupBy("warehouse","product").agg(max("amount").as("MaxAmount"),min("amount")
        .as("MinAmount"),avg("amount").as("AverageAmount")).orderBy("warehouse","product")
        .drop("eventTime","positionID").show()
    }
}
