package crosswords

import com.theguardian.crosswords.api.client.models._

object Grid {
  def empty(cols: Int, rows: Int) = Grid(cols, rows, Map.empty)

  def fromCrossword(crossword: Crossword) = {
    val grid = empty(crossword.dimensions.cols, crossword.dimensions.rows)

    crossword.entries.foldLeft(grid) { (acc, entry) =>
      entry.coordinates.map(_ -> White).foldLeft(acc) { _ + _ }
    }
  }
}

sealed trait Square
case object White extends Square
case object Black extends Square

case class Grid(cols: Int, rows: Int, entries: Map[(Int, Int), Square]) {
  require(cols > 0, "Grid must have at least one column")
  require(rows > 0, "Grid must have at least one row")

  def get(x: Int, y: Int) = entries.get((x, y)).getOrElse(Black)

  def +(entry: ((Int, Int), Square)) = {
    val ((x, y), _) = entry

    require(x >= 0, "x co-ordinate must be greater than or equal to 0")
    require(y >= 0, "y co-ordinate must be greater than or equal to 0")
    require(x < cols, "x co-ordinate is past the edge of the grid")
    require(y < rows, "y co-ordinate is past the edge of the grid")

    copy(entries = entries + entry)
  }
}
