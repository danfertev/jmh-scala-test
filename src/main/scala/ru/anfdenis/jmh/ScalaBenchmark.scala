package ru.anfdenis.jmh


/**
 * Denis Anfertev
 * 16.05.13 16:54
 */
object ScalaBenchmark {
  def testToBuffer() = {
    (0 to 1000000).toBuffer
  }

  def testToBufferWhile() = {
    var i = 0
    val ab = new collection.mutable.ArrayBuffer[Int]()
    while (i < 1000000) {
      ab += i
      i += 1
    }
    ab
  }

  def testToList() = {
    (0 to 1000000).toList
  }

  def testToListRecursion() = {
    toListRecursion(999999, Nil)
  }

  def toListRecursion(x: Int, xs: List[Int]): List[Int] =
    if (x < 0) xs
    else toListRecursion(x - 1, x :: xs)

  private val xs = (0 to 1000000).toList

  def testWithFilterMap() = {
    xs.withFilter(_ > 500000).map(_ * 2)
  }

  def testFilterMap() = {
    xs.filter(_ > 500000).map(_ * 2)
  }

  private def func(v: Int) = (_: Int) < v

  private val x = 500000

  private def clojure = (_: Int) < x

  def testFunc() = {
    xs.filter(func(x))
  }

  def testClojure() = {
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

  def testSum() = {
    sum(Vec[Int]((1 to l).toArray))
  }

  def testSumWithSpec() = {
    sumWithSpec(VecWithSpec[Int]((1 to l).toArray))
  }

  def isSortedList[A](as: List[A], ord: (A, A) => Boolean): Boolean = {
    def step(xs: List[A]): Boolean = xs match {
      case List() => true
      case List(y) => true
      case ys@List(y1, y2, _*) => if (ord(y1, y2)) step(ys.tail) else false
    }
    step(as)
  }

  def testIsSortedList(n: Int) = {
    isSortedList[Int]((0 to n).toList, _ - _ < 0)
  }


  def isSortedArray[A](as: Array[A], ord: (A, A) => Boolean): Boolean = {
    val n = as.length
    def step(k: Int): Boolean = {
      if (k == 0) true
      else if (ord(as(k - 1), as(k))) step(k - 1) else false
    }
    if (n < 2) true else step(n - 1)
  }


  def isSortedArray2[A](xs: Array[A], ord: (A, A) => Boolean): Boolean = {
    val n = xs.length
    if (n <= 2) true
    else (1 until n).forall(x => ord(xs(x - 1), xs(x)))
  }

  def isSortedArray3[A](as: Array[A], ord: (A, A) => Boolean): Boolean = {
    val n = as.length
    if (n < 2) true
    else {
      var k = n - 1
      while (k != 0 && ord(as(k - 1), as(k))) {
        k -= 1
      }
      k == 0
    }
  }

  def testIsSortedArray_0toN(n: Int) = {
    isSortedArray[Int]((0 to n).toArray, _ - _ < 0)
  }

  def testIsSortedArray2_0toN(n: Int) = {
    isSortedArray2[Int]((0 to n).toArray, _ - _ < 0)
  }

  def testIsSortedArray3_0toN(n: Int) = {
    isSortedArray3[Int]((0 to n).toArray, _ - _ < 0)
  }

  def testIsSortedArray_Nto0(n: Int) = {
    isSortedArray[Int]((n to 0 by -1).toArray, _ - _ > 0)
  }

  def testIsSortedArray2_Nto0(n: Int) = {
    isSortedArray2[Int]((n to 0 by -1).toArray, _ - _ > 0)
  }

  def testIsSortedArray3_Nto0(n: Int) = {
    isSortedArray3[Int]((n to 0 by -1).toArray, _ - _ > 0)
  }
}
