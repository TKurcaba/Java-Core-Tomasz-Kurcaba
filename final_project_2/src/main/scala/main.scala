import org.apache.spark.sql.{DataFrame}
import org.apache.log4j.Logger

object main {
  lazy val log: Logger = Logger.getLogger(getClass.getName)

  def main(args: Array[String]) {
    if (args.length != 4) {
      log.error("Bad number of files")
      return
    }

    val dataReader = DataReader
    val messageDf: DataFrame = dataReader.readAvro(args(0))
    val user_dirDf: DataFrame = dataReader.readAvro(args(1))
    val message_dirDf: DataFrame = dataReader.readAvro(args(2))
    val retweetDf: DataFrame = dataReader.readAvro(args(3))
    val transformation = Transformation


    val result = transformation.filterFirstSecondWaves(retweetDf, messageDf)
    transformation.joinAndPrepareToPrint(result, messageDf, message_dirDf, user_dirDf).show()
  }

}
