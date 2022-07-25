import org.apache.spark.sql.SparkSession

trait SparkSessionProvider {
  private val spark = SparkSession.builder().master("local[4]").appName("FinalProject").getOrCreate()

  def getSpark : SparkSession  = spark
}
