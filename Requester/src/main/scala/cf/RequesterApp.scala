package cf

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory

object RequesterApp extends App {
  println("RequesterApp starts")
  val system = ActorSystem("RequesterSystem", ConfigFactory.load("requester"))

  system.actorOf(Props[RequestReporter], name = "reporter")
}
