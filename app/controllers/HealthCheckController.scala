package controllers

import play.api.mvc.{Controller, Action}
import data.CrosswordsApi
import grizzled.slf4j.Logging
import lib.const
import scala.concurrent.ExecutionContext.Implicits.global

object HealthCheckController extends Controller with Logging {
  def healthCheck = Action.async {
    CrosswordsApi.client.forToday.map(const(Ok("Everything ok"))) recover {
      case error =>
        logger.error("Got error trying to communicate with crosswords API", error)
        ServiceUnavailable("Could not contact crosswords API")
    }
  }
}
