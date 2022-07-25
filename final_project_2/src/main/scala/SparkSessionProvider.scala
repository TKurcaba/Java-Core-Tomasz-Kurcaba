import org.apache.spark.sql.SparkSession

trait SparkSessionProvider {
  private val spark = SparkSession.builder().master("local[4]").appName("final_project_2").getOrCreate()
  def getSpark : SparkSession = spark
}
