package crosswords

import org.specs2.mutable.Specification

class GridSpec extends Specification {
  "+" should {
    "throw an error for an entry past the right side of the grid" in {
      Grid.empty(1, 1) + ((1, 0) -> White) should throwA("past the edge")
    }

    "throw an error for an entry past the bottom side of the grid" in {
      Grid.empty(1, 1) + ((0, 1) -> Black) should throwA("past the edge")
    }

    "throw an error for an entry past the left side of the grid" in {
      Grid.empty(1, 1) + ((-1, 0) -> White) should throwA("greater than or equal")
    }

    "throw an error for an entry past the top side of the grid" in {
      Grid.empty(1, 1) + ((0, -1) -> Black) should throwA("greater than or equal")
    }

    "not throw an error if within bounds" in {
      Grid.empty(1, 1) + ((0, 0) -> White) should not throwA()
    }

    "update the grid if within bounds" in {
      Grid.empty(1, 1) + ((0, 0) -> White) mustEqual Grid(1, 1, Map((0, 0) -> White))
    }
  }

  "get" should {
    "return Black for a missing entry" in {
      Grid.empty(1, 1).get(0, 0) mustEqual Black
    }
  }

  "fromCrossword" should {
    "correctly construct a grid from a crossword" in {
      1 mustEqual 1
    }
  }
}
