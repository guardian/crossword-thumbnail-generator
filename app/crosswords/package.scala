import com.gu.crosswords.api.client.models.{Down, Across, Position, Entry}

package object crosswords {
  implicit class RichEntry(entry: Entry) {
    def coordinates: Seq[(Int, Int)] = {
      val Position(x, y) = entry.position

      0 until entry.length map { increment =>
        entry.direction match {
          case Across =>
            (x + increment, y)
          case Down =>
            (x, y + increment)
        }
      }
    }
  }
}
