import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO

package object lib {
  def const[A, B](b: B): A => B = _ => b

  implicit class RichBufferedImage(bufferedImage: BufferedImage) {
    def toPngBytes = {
      val outputStream = new ByteArrayOutputStream()
      ImageIO.write(bufferedImage, "png", outputStream)
      outputStream.flush()
      val byteArray = outputStream.toByteArray
      outputStream.close()
      byteArray
    }
  }
}
