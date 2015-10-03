package exercise6

import org.junit._

class Exercise6Test {

  import RNG._

  @Test def exercise6_1_zero: Unit = {
    assert(nonNegativeInt(MockRNG(0))._1 == 0)
  }
  @Test def exercise6_1_one: Unit = {
    assert(nonNegativeInt(MockRNG(1))._1 == 1)
  }
  @Test def exercise6_1_minus_one: Unit = {
    assert(nonNegativeInt(MockRNG(-1))._1 == 1)
  }
  @Test def exercise6_1_max_value: Unit = {
    assert(nonNegativeInt(MockRNG(Int.MaxValue))._1 == Int.MaxValue)
  }
  @Test def exercise6_1_min_value: Unit = {
    assert(nonNegativeInt(MockRNG(Int.MinValue))._1 == 0)
  }

  case class MockRNG(as: Int*) extends RNG {
    def nextInt: (Int, RNG) = (as.head, MockRNG(as.tail: _*))
  }
}