import com.gu.conf.Configuration

package object conf {
  implicit class RichConfiguration(configuration: Configuration) {
    def getRequiredStringProperty(propertyName: String) = configuration.getStringProperty(propertyName) getOrElse {
      throw new RuntimeException(s"$propertyName must be set in config")
    }
  }
}
