package conf

import com.gu.management._
import com.gu.management.HttpRequest
import com.gu.management.logback.LogbackLevelPage

object CrosswordThumbnailManagement extends com.gu.management.play.Management {
  override val applicationName: String = "crossword-thumbnail-generator"

  val healthCheckPage = new HealthcheckManagementPage {
    override def get(req: HttpRequest) = {
      /** TODO, add health check that we are able to load some config? */
      PlainTextResponse("OK")
    }
  }

  object RequestMetrics extends com.gu.management.play.RequestMetrics.Standard

  /** TODO, add metrics for how long it takes to generate an image and crosswords api response times */
  val allMetrics = RequestMetrics.asMetrics

  override val pages: List[ManagementPage] = List(
    new ManifestPage,
    healthCheckPage,
    StatusPage(applicationName, allMetrics),
    new LogbackLevelPage(applicationName)
  )
}