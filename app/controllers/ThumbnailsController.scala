package controllers

import play.api.mvc.{Action, Controller}
import scala.concurrent.Future
import data.CrosswordsApi
import com.theguardian.crosswords.api.client.models.Type
import crosswords.{ThumbMaker, Grid}
import scala.concurrent.ExecutionContext.Implicits.global

case class CrosswordInfo(name: String, uri: String)

object ThumbnailsController extends Controller {
  val IndexCellSize = 10
  val IndexBorderSize = 1

  def index = Action.async {
    CrosswordsApi.client.forToday map { todaysCrosswords =>
      val crosswords = todaysCrosswords.crosswords.values map { crossword =>
        val uri = routes.ThumbnailsController.crossword(
          Type.byType(crossword.`type`),
          crossword.number,
          IndexCellSize,
          IndexBorderSize
        ).toString

        CrosswordInfo(crossword.name, uri)
      }

      Ok(views.html.index(crosswords.toList))
    }
  }

  def crossword(crosswordType: String, number: Int, cellSize: Int, borderSize: Int) = Action.async {
    for {
      crossword <- CrosswordsApi.client.getCrossword(Type.fromString(crosswordType).get, number)
      grid = Grid.fromCrossword(crossword)
      imageData <- Future {
        ThumbMaker.draw(grid, cellSize, borderSize)
      }
    } yield Ok(imageData).as("image/png")
  }
}
