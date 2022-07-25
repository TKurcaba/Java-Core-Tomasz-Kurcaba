package DataSetClasses
import org.apache.spark.sql.{Encoder, Encoders}

case class Amounts(positionId:Long, amount:BigDecimal, eventTime:Long)


object Amounts {
  implicit val amountEncoder: Encoder[Amounts] = Encoders.product[Amounts]
}