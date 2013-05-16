package ru.anfdenis.jmh


/**
 * Denis Anfertev
 * 16.05.13 16:54
 */
object ScalaBenchmark {
  def testToBuffer() {
    (0 to 1000000).toBuffer
  }

  def testToBufferWhile() {
    var i = 0
    val ab = new collection.mutable.ArrayBuffer[Int]()
    while (i < 1000000) {
      ab += i
      i += 1
    }
  }

  def testToList() {
    (0 to 1000000).toList
  }

  def testToListRecursion() {
    toListRecursion(999999, Nil)
  }

  def toListRecursion(x: Int, xs: List[Int]): List[Int] =
    if (x < 0) xs
    else toListRecursion(x - 1, x :: xs)
}
