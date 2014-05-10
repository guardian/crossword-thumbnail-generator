package controllers

import play.api.mvc.{Action, Controller}
import scala.concurrent.Future
import data.CrosswordsApi
import com.theguardian.crosswords.api.client.models.Type
import crosswords.{ThumbMaker, Grid}
import scala.concurrent.ExecutionContext.Implicits.global

object ThumbnailsController extends Controller {
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
