package cf

import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorLogging}
import akka.serialization.Serialization.serializedActorPath

class Requester extends Actor with ActorLogging {
  log.debug("-------------------------")
  log.debug("Requester logically at: " + self.path)
  log.debug("Requester physically at: " + serializedActorPath(self))
  override def receive: Receive = {
    case m =>
      log.debug("-------------------------")
      log.debug("receive: "  + m)
      sender ! "reporter sends: " + m
  }
}
