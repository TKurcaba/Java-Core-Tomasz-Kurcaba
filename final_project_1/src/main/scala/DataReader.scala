import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType

object DataReader extends SparkSessionProvider {


  def readCsv(path: String, header : Boolean, schema: StructType) : DataFrame = {
    getSpark.read.format("csv").option("header", header).schema(schema).load(path)
  }

}
