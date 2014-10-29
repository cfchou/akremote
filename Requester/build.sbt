import AssemblyKeys._

assemblySettings

jarName in assembly := "requester.jar"

name := "requester"

organization  := "feng"

version := "1.0"

scalaVersion  := "2.11.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-client"  % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-remote"   % akkaV,
    "com.typesafe.akka"   %%  "akka-kernel"   % akkaV,
    "com.typesafe.akka"   %%  "akka-slf4j"    % akkaV,
    "org.apache.kafka"    %   "kafka_2.10"    % "0.8.1.1"
      exclude("javax.jms", "jms")
      exclude("com.sun.jdmk", "jmxtools")
      exclude("com.sun.jmx", "jmxri"),
    "org.scalacheck"      %%  "scalacheck"    % "1.11.6"
  )
}

Revolver.settings
