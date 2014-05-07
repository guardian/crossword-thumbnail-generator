import sbt._

object Dependencies {
  val clapper = "org.clapper" %% "grizzled-slf4j" % "1.0.1"

  val specs2 = "org.specs2" %% "specs2" % "2.2.3"

  val guardianManagementPlay = "com.gu" %% "management-play" % "6.0" exclude("javassist", "javassist")

  val guardianConfiguration = "com.gu" %% "configuration" % "3.9"

  val crosswordsApiClient = "com.theguardian" %% "crosswords-api-client" % "0.3-SNAPSHOT" changing()
}