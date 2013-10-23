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

  val n = 10000000
  val array = (1 to n).toArray

  import scala.concurrent.Future
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Await
  import scala.concurrent.duration._

  def isSorted(as: Array[Int], ord: (Int, Int) => Boolean): Boolean = {
    val n = as.length
    def step(k: Int): Boolean = {
      if (k == 0) true
      else if (ord(as(k - 1), as(k))) step(k - 1) else false
    }
    if (n < 2) true else step(n - 1)
  }

  def isSortedPar(xs: Array[Int], ord: (Int, Int) => Boolean): Future[Boolean] = {
    val size = xs.size
    type Index = (Int, Int)
    def slice: Array[Index] = {
      val factor = size / 32
      if (factor < 2) Array((0, size - 2))
      else {
        val ix = 0 until size - 1 by factor
        (if (ix.last != size - 2 || ix.last == size - 1) ix :+ (size - 2) else ix).sliding(2).toArray.map(i => (i(0), i(1)))
      }
    }

    def isSliceSorted(ix: Index) = {
      val (start, end) = ix
      var res = true
      var i = start
      while (i <= end && res) {
        if (!ord(xs(i), xs(i + 1))) res = false
        i += 1
      }
      res
    }

    def par(indexes: Array[Index]): Array[Future[Boolean]] = {
      indexes map {
        ix => Future(isSliceSorted(ix))
      }
    }

    val result = par(slice)

    def track(fs: Array[Future[Boolean]]): Future[Boolean] = {
      if (fs.isEmpty) Future(true)
      else {
        val first = Future.firstCompletedOf(result)
        first.flatMap {
          r => if (r) track(fs.filter(f => first == f)) else Future(false)
        }
      }
    }
    track(result)
  }

  def isSortedParDi(xs: Array[Int], size: Int): Future[Boolean] = {
    def loop(coords: Seq[(Int, Int)]): Future[Seq[Boolean]] = {
      Future.sequence(coords map {
        case (start: Int, end: Int) =>
          Future(check(start, end))
      })
    }
    def check(start: Int, end: Int): Boolean = {
      var ind = start
      var res = true
      while (ind < end) {
        if (xs(ind) > xs(ind + 1)) res = false
        ind = ind + 1
      }
      res
    }
    val indicies: Seq[(Int, Int)] = {
      val b = size / 16

      if (b > 10) {
        val x = (0 until (size - 1) by b).sliding(2).toVector.map(is => (is(0), is(1)))
        x.init :+(x.last._1, size - 2)
      } else List((0, size - 2))
    }
    loop(indicies).map(_.forall(_ == true))
  }

  def testIsSorted = {
    Await.result(Future(isSorted(array, _ < _)), 30.seconds)
  }

  def testIsSortedPar = {
    Await.result(isSortedPar(array, _ < _), 30.seconds)
  }

  def testIsSortedParDi = {
    Await.result(isSortedParDi(array, n), 30.seconds)
  }


}
