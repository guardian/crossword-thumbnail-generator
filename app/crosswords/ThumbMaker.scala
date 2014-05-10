package crosswords

import java.awt.image.BufferedImage
import java.awt.Color
import lib._

object ThumbMaker {
  val BackgroundColour = Color.black
  val ForegroundColour = Color.white

  /** Draws the grid as a black and white image */
  def draw(grid: Grid, cellSize: Int, borderSize: Int) = {
    val width = grid.cols * (cellSize + borderSize) + borderSize
    val height = grid.rows * (cellSize + borderSize) + borderSize

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
      val x = col * (cellSize + borderSize) + borderSize
      val y = row * (cellSize + borderSize) + borderSize

      graphics.fillRect(x, y, cellSize, cellSize)
    }

    image.toPngBytes
  }
}
