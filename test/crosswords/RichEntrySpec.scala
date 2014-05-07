package crosswords

import org.specs2.mutable.Specification
import com.theguardian.crosswords.api.client.models._

class RichEntrySpec extends Specification {
  "coordinates" should {
    "return a list of co-ordinates over which an Across entry extends" in {
      val fixture = Entry("", Across, None, Nil, "", "", 4, 0, Position(3, 5), SeparatorLocations(None), None)

      fixture.coordinates mustEqual Seq(
        (3, 5), (4, 5), (5, 5), (6, 5)
      )
    }

    "return a list of co-ordinates over which a Down entry extends" in {
      val fixture = Entry("", Down, None, Nil, "", "", 4, 0, Position(3, 5), SeparatorLocations(None), None)

      fixture.coordinates mustEqual Seq(
        (3, 5), (3, 6), (3, 7), (3, 8)
      )
    }
  }
}
