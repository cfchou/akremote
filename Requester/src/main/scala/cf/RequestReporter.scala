package cf


import akka.actor.{Actor, ActorLogging}
import akka.serialization.Serialization.serializedActorPath

class RequestReporter extends Actor with ActorLogging {
  println("=========================")
  println("Reporter logically at: " + self.path)
  println("Reporter physically at: " + serializedActorPath(self))
  override def receive: Receive = {
    case m =>
      log.debug("=========================")
      log.debug("receive: "  + m)
      sender ! "reporter sends: " + m
  }
}
