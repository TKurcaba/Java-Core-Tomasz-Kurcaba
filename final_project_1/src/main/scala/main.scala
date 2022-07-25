import DataSetClasses.{Amounts, Warehouse}
import org.apache.spark.sql.types.{DataTypes, DecimalType, LongType, StringType, StructField, StructType, TimestampType}


object main extends SparkSessionProvider {

  def main(args: Array[String]): Unit ={
    val dataReader = DataReader
    val calculations = Calculations
    val schema1 = StructType(Array(
      StructField("positionID",LongType,true),
      StructField("warehouse",StringType,true),
      StructField("product",StringType,true),
      StructField("eventTime", LongType, true),
    ))
    val DecimalType = DataTypes.createDecimalType(7, 5)

    val schema2 = StructType(Array(
      StructField("positionID",LongType,true),
      StructField("amount",DecimalType,true),
      StructField("eventTime", LongType, true),
    ))
    val dfWarehouse = dataReader.readCsv(args(0), false, schema1).as[Warehouse]
    val dfAmounts = dataReader.readCsv(args(1), false, schema2).as[Amounts]
    val dfJoined = dfWarehouse.drop("eventTime").join(dfAmounts, Seq("positionID"))
    calculations.getCurrentAmount(dfJoined)
    calculations.describeWarehouse(dfJoined)
  }
}
