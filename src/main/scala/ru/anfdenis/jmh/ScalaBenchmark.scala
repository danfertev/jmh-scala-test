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

  private val xs = (0 to 1000000).toList

  def testWithFilterMap() {
    xs.withFilter(_ > 500000).map(_ * 2)
  }

  def testFilterMap() {
    xs.filter(_ > 500000).map(_ * 2)
  }

  private def func(v: Int) = (_: Int) < v

  private val x = 500000

  private def clojure = (_: Int) < x

  def testFunc() {
    xs.filter(func(x))
  }

  def testClojure() {
    xs.filter(clojure)
  }

  private case class Vec[T](v: Array[T]) {
    def apply(n: Int): T = v(n)
  }

  private case class VecWithSpec[@specialized(Int) T](v: Array[T]) {
    def apply(n: Int): T = v(n)
  }

  private def sum(xs: Vec[Int]) = {
    var s = 0
    for (x <- 0 until xs.v.length) {
      s += xs(x)
    }
    s
  }

  private def sumWithSpec(xs: VecWithSpec[Int]) = {
    var s = 0
    for (x <- 0 until xs.v.length) {
      s += xs(x)
    }
    s
  }

  private val l = 1000000

  def testSum() {
    sum(Vec[Int]((1 to l).toArray))
  }

  def testSumWithSpec() {
    sumWithSpec(VecWithSpec[Int]((1 to l).toArray))
  }
}
