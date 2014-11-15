import AssemblyKeys._

assemblySettings

jarName in assembly := "requester.jar"

name := "requester"

organization  := "feng"

version := "1.0"

scalaVersion  := "2.10.4"

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
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "org.apache.kafka"    %%  "kafka"         % "0.8.1.1"
      exclude("javax.jms", "jms")
      exclude("com.sun.jdmk", "jmxtools")
      exclude("com.sun.jmx", "jmxri"),
    "org.clapper"         %%  "grizzled-slf4j"  % "1.0.2",
    "ch.qos.logback"      %   "logback-classic" % "1.1.2",
    "org.scalacheck"      %%  "scalacheck"      % "1.11.6",
    "org.scalatest"       %%  "scalatest"       % "2.2.1"   % "test"
  )
}

Revolver.settings
