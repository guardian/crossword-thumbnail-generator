package data

import com.theguardian.crosswords.api.client.{Response, Http, ApiClient}
import conf.CrosswordThumbnailGeneratorConfig
import scala.concurrent.Future
import play.api.libs.ws.WS
import scala.concurrent.ExecutionContext.Implicits.global

object CrosswordsApi {
  val client = ApiClient(CrosswordThumbnailGeneratorConfig.apiKey, new Http {
    override def get(uri: String): Future[Response] = WS.url(uri).get() map { response =>
      Response(response.status, response.statusText, response.body)
    }
  })
}
