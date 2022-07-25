
import org.apache.spark.sql.DataFrame

object DataReader extends SparkSessionProvider {
  def readAvro(path: String) : DataFrame = {
    getSpark.read.format("avro").load("data/"+path)
  }
}
