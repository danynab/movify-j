name := """movify-j"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  "commons-codec" % "commons-codec" % "1.10",
  "com.paypal.sdk" % "rest-api-sdk" % "1.1.2",
  "org.apache.commons" % "commons-lang3" % "3.3.2"
)
