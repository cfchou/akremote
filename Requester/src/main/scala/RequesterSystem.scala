
import akka.actor.{Actor, ActorSystem, Props}
import akka.kernel.Bootable
import cf.RequestReporter
import com.typesafe.config.ConfigFactory

class RequesterSystem extends Bootable {
  val system = ActorSystem("RequesterSystem", ConfigFactory.load("requester"))

  override def startup(): Unit = {
    system.actorOf(Props[RequestReporter], name = "reporter")

  }

  override def shutdown(): Unit = {
    system.shutdown()
  }
}
