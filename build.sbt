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
  "org.apache.commons" % "commons-lang3" % "3.3.2",
  "org.glassfish.jersey.core" % "jersey-client" % "2.16",
  "org.glassfish.jersey.media" % "jersey-media-json-jackson" % "2.16",
  "javax.ws.rs" % "javax.ws.rs-api" % "2.0.1",
  "javax.ws.rs" % "jsr311-api" % "1.1.1"
)
