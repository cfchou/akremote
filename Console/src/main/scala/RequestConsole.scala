import akka.actor.{Props, ActorSystem}
import cf.Requester
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.{Promise, Await, Future}
import scala.util.Try
import scala.concurrent.duration._

import grizzled.slf4j.Logger

object RequestConsole extends App {
  val log = Logger[this.type]
  if (args.length > 0) {
    log.info("hi, args(0):" ++ args(0))
  } else {
    log.info("hello")
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

  Try(Await.result(rep, askDura)).recover {
    case _ => "!!!!!!! no response from the reporter"
  }.map(println _)

  Try(Await.result(req, askDura)).recover {
    case _ => "!!!!!!! no response from the requester"
  }.map(println _)

  println("------------------")
  val never = Promise[Unit].future

  Try(Await.result(never.mapTo[String], 10.seconds))
  println("Shutdown!")
  /*
  println(Try(Await.result(never.mapTo[String], 10.seconds)).
    getOrElse("Shutdown!"))
    */
  println("------------------")

  system.shutdown()
}
