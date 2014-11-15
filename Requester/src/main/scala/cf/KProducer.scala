package cf

import java.util.Properties

import akka.actor.{Actor, ActorLogging}
import kafka.producer.{Producer, ProducerConfig}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}

object KProducer {
  case object Tick
  case object Yo
}

class KProducer(val conf: KProducerConf) extends Actor with ActorLogging {

  import cf.KProducer._

  println(">>> KProducer")
  log.info("Hello ------------------")

  val producer = {
    val props = new Properties();
    if (null != conf.properties) {
      conf.properties foreach { kv =>
        props.put(kv._1, kv._2)
      }
      val config: ProducerConfig = new ProducerConfig(props);
      Try {
        new Producer[String, String](config)
      } match {
        case Success(v) => v
        case Failure(e) =>
          log.info("KProducer: " + e)
          throw e
      }
    } else {
      throw new IllegalArgumentException("conf.properties is null")
    }
  }

  implicit val dispatcher = context.dispatcher

  context.system.scheduler.schedule(1.seconds, 1.seconds, self, Tick)

  override def receive: Receive = {
    case Tick =>
      log.info("Tick------------------")
      sender ! Yo
    case _ =>
      log.info("Unknown ------------------")
  }

}

