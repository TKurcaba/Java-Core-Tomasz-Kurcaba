package DataSetClasses


import org.apache.spark.sql.{Encoder, Encoders}

case class Warehouse(positionId: Long, warehouse: String, product: String, eventTime: Long)

object Warehouse {
  implicit val warehouseEncoder: Encoder[Warehouse] = Encoders.product[Warehouse]
}