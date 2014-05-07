package conf

import com.gu.conf.ConfigurationFactory

object CrosswordThumbnailGeneratorConfig {
  private val conf = ConfigurationFactory.getConfiguration("crossword-thumbnail-generator")

  lazy val apiKey = conf.getRequiredStringProperty("crosswords_api.key")

}
