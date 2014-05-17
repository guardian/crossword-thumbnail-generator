package conf

import play.api.Play.current

object CrosswordThumbnailGeneratorConfig {
  val config = current.configuration

  lazy val apiKey = config.getRequiredStringProperty("crosswords_api.key")
}
