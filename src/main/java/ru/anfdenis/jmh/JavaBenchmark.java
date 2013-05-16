package ru.anfdenis.jmh;

import org.openjdk.jmh.annotations.BenchmarkType;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Measurement;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Denis Anfertev
 * 16.05.13 17:05
 */
public class JavaBenchmark {
    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void testToBuffer() {
        ScalaBenchmark.testToBuffer();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void testToBufferWhile() {
        ScalaBenchmark.testToBufferWhile();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void testToList() {
        ScalaBenchmark.testToList();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void testToListRecursion() {
        ScalaBenchmark.testToListRecursion();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 10, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public void testLinkedList() {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }

}
