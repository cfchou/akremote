package cf

import scala.collection.immutable.HashMap

trait KProducerConf {
  var properties: Map[String, String] = HashMap(
    ("metadata.broker.list", "broker1:9092,broker2:9093"),
    ("serializer.class", "kafka.serializer.StringEncoder"),
    ("request.required.acks", "1"))
}
