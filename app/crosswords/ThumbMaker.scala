package crosswords

import java.awt.image.BufferedImage
import java.awt.Color
import lib._

object ThumbMaker {
  val CellSize = 20
  val BorderSize = 1
  val BackgroundColour = Color.black
  val ForegroundColour = Color.white

  /** Draws the grid as a black and white image */
  def draw(grid: Grid) = {
    val width = grid.cols * (CellSize + BorderSize) + BorderSize
    val height = grid.rows * (CellSize + BorderSize) + BorderSize

    /** As it's only going to be black & white anyway, gray scale should be fine */
    val image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)

    val graphics = image.createGraphics()

    graphics.setColor(BackgroundColour)
    graphics.drawRect(0, 0, width, height)

    graphics.setColor(ForegroundColour)
    graphics.setBackground(ForegroundColour)

    for (
      col <- 0 until grid.cols;
      row <- 0 until grid.rows if grid.get(col, row) == White
    ) {
      val x = col * (CellSize + BorderSize) + BorderSize
      val y = row * (CellSize + BorderSize) + BorderSize

      graphics.fillRect(x, y, CellSize, CellSize)
    }

    image.toPngBytes
  }
}
