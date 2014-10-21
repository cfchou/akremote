import akka.actor.{Props, ActorSystem}
import cf.Requester
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Await, Future }
import scala.util.Try
import scala.concurrent.duration._

object RequestConsole extends App {
  if (args.length > 0) {
    println("hi, args(0):" ++ args(0))
  } else {
    println("hello")
  }

  // local system
  val system = ActorSystem("console")

  // lookup an actor on a remote node
  val rpath = "akka.tcp://RequesterSystem@127.0.0.1:2553/user/reporter"
  val reporter = system.actorSelection(rpath)

  implicit val askDura = 5.seconds
  implicit val askTimeOut: Timeout = askDura

  val rep = (reporter ? "to reporter").mapTo[String]

  // create an actor on a remote node
  val requester = system.actorOf(Props[Requester], "requester")
  val req = (requester ? "to requester").mapTo[String]

  Try(Await.result(rep, 5.seconds)).recover {
    case _ => "!!!!!!! no response from the reporter"
  }.map(println _)

  Try(Await.result(req, askDura)).recover {
    case _ => "!!!!!!! no response from the requester"
  }.map(println _)

  system.shutdown()
}
