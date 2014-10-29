name := "request-console"

organization  := "feng"

version := "1.0"

scalaVersion  := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

seq(bintrayResolverSettings:_*)

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-remote"   % akkaV,
    "com.typesafe.akka"   %%  "akka-slf4j"    % akkaV,
    "org.apache.kafka"    %   "kafka_2.10"    % "0.8.1.1"
      exclude("javax.jms", "jms")
      exclude("com.sun.jdmk", "jmxtools")
      exclude("com.sun.jmx", "jmxri"),
    "ch.qos.logback"      %   "logback-classic" % "1.1.2",
    "org.clapper"         %%  "grizzled-slf4j"  % "1.0.2",
    "org.scalacheck"      %%  "scalacheck"    % "1.11.6"
  )
}

