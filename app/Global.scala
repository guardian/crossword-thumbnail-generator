import conf.CrosswordThumbnailManagement.RequestMetrics
import play.api.mvc._

object Global extends WithFilters(RequestMetrics.asFilters: _*)
