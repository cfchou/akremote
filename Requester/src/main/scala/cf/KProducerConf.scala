package cf

import scala.collection.immutable.HashMap

trait KProducerConf {
  var properties: Map[String, String] = HashMap(
    ("metadata.broker.list", "localhost:9092,127.0.0.1:9093"),
    ("serializer.class", "kafka.serializer.StringEncoder"),
    ("request.required.acks", "1"))
}
