import sbt._
import Keys._
import com.gu.deploy.MagentaArtifact._
import sbtassembly.Plugin._
import AssemblyKeys._
import play.Project._
import Dependencies._

object CrosswordThumbnailGeneratorBuild extends Build {
  val projectVersion = "0.1"

  def commonPlayProject(name: String, execName: String, path: String) = 
    play.Project(name, projectVersion, path=file(path))
      .settings(magentaArtifactSettings: _*)
      .settings(
        executableName := execName,
        testOptions in Test := Nil,
        mergeStrategy in assembly <<= (mergeStrategy in assembly) {
          (old) => {
            case PathList("gu-conf", xs@_*) => MergeStrategy.filterDistinctLines
            case PathList("scalax", xs@_*) => MergeStrategy.first
            case PathList("play", xs@_*) => MergeStrategy.first
            case PathList("org", "apache", "commons", "logging", xs@_*) => MergeStrategy.first
            case PathList("org", "mockito", xs@_*) => MergeStrategy.first
            case PathList("org", "hamcrest", xs@_*) => MergeStrategy.first
            case PathList("scala", "concurrent", "stm", xs @ _*) => MergeStrategy.first
            case x => old(x)
          }
        }
    )

  val crosswordThumbnailGenerator = commonPlayProject(
    "crossword-thumbnail-generator",
    "crossword-thumbnail-generator",
    "."
  ).settings(
    resolvers += "Guardian GitHub Releases" at "http://guardian.github.com/maven/repo-releases",
    libraryDependencies ++= Seq(
      clapper,
      crosswordsApiClient,
      specs2
    )
  )
}
