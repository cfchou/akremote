package cf

import java.util.Properties

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorLogging}

class KProducer extends Actor with ActorLogging { conf: KProducerConf =>
  val props = new Properties();
  conf.properties foreach { kv =>
    props.put(kv._1, kv._2)
  }

  override def receive: Actor.Receive = ???

}
