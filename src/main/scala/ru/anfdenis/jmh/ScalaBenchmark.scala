package ru.anfdenis.jmh


/**
 * Denis Anfertev
 * 16.05.13 16:54
 */
object ScalaBenchmark {
  def test() {
    (0 to 1000000).toBuffer
  }
}
