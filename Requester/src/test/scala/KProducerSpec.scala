import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestActorRef, TestKit, ImplicitSender}
import org.scalatest.{Matchers, WordSpecLike, BeforeAndAfterAll}
import cf.{KProducerConf, KProducer}

import scala.collection.immutable.HashMap

class KProducerSpec extends
  TestKit(ActorSystem("KProducerSpec")) with
  ImplicitSender with
  WordSpecLike with
  Matchers with
  BeforeAndAfterAll {

  override protected def afterAll(): Unit = {
    println("Tests done, shutdown.")
    system.shutdown()
  }

  "KProducer" should {
    "react to Tick " in {
      val p = TestActorRef[KProducer](Props(classOf[KProducer],
        new KProducerConf {}))

      // if ImplictSender isn't mixed in:
      //p.tell(KProducer.Tick, testActor)

      p ! KProducer.Tick
      expectMsg(KProducer.Yo)
    }
  }
}
