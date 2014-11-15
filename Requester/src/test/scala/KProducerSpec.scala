import java.util.Properties

import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestActorRef, TestKit, ImplicitSender}
import kafka.utils.{ZkUtils, ZKStringSerializer}
import org.I0Itec.zkclient.ZkClient
import org.scalacheck.Gen
import org.scalatest.{Matchers, WordSpecLike, BeforeAndAfterAll}
import cf.{KProducerConf, KProducer}
import kafka.admin.AdminUtils
import grizzled.slf4j.Logger

class KProducerSpec extends
  TestKit(ActorSystem("KProducerSpec")) with
  ImplicitSender with
  WordSpecLike with
  Matchers with
  BeforeAndAfterAll {

  val log = Logger[this.type]

  val sessionTimeoutMs = 10000
  val connectionTimeoutMs = 10000
  val zkClient = new ZkClient("localhost:2181", sessionTimeoutMs,
    connectionTimeoutMs, ZKStringSerializer)

  val topic = classOf[KProducerSpec].toString.replaceAll("[ \\._-]", "_")
  val nPartitions = 3
  val replicationFactor = 2 // <= number of kafka brokers
  val topicConfig = new Properties

  val kp = TestActorRef[KProducer](Props(classOf[KProducer],
    new KProducerConf {}, topic))

  override protected def beforeAll(): Unit = {
    super.beforeAll()

    log.debug(s"prepare: delete topic: $topic")
    zkClient.deleteRecursive(ZkUtils.getTopicPath(topic))
    log.debug(s"create topic: $topic")
    AdminUtils.createTopic(zkClient, topic, nPartitions, replicationFactor,
      topicConfig)

  }

  override protected def afterAll(): Unit = {
    log.debug("Tests done: delete topics(messages retained), shutdown.")
    // NOTE leave the topic for latter manual examination
    //zkClient.deleteRecursive(ZkUtils.getTopicPath(topic))
    system.shutdown()
  }

  "KProducer" should {
    "react to Tick" in {

      // NOTE if ImplictSender isn't mixed in, need to explicitly use testActor
      //kp.tell(KProducer.Tick, testActor)

      kp ! KProducer.Tick
      expectMsg(KProducer.Yo)
    }

    "send a key and un-keyed message, respectively" in {
      kp ! KProducer.KeyedMsg(None, "only value123")
      kp ! KProducer.KeyedMsg(Some("key123"), "and value456")
    }
  }

}
