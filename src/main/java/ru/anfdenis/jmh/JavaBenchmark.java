package ru.anfdenis.jmh;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Denis Anfertev
 * 16.05.13 17:05
 */
public class JavaBenchmark {
    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testToBuffer() {
        return ScalaBenchmark.testToBuffer();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testToBufferWhile() {
        return ScalaBenchmark.testToBufferWhile();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testToList() {
        return ScalaBenchmark.testToList();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testToListRecursion() {
        return ScalaBenchmark.testToListRecursion();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testLinkedList() {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        return list;
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testWithFilterMap() {
        return ScalaBenchmark.testWithFilterMap();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testFilterMap() {
        return ScalaBenchmark.testFilterMap();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testFunc() {
        return ScalaBenchmark.testFunc();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testClojure() {
        return ScalaBenchmark.testClojure();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testSum() {
        return ScalaBenchmark.testSum();
    }

    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
    public Object testSumVecWithSpect() {
        return ScalaBenchmark.testSumWithSpec();
    }
}
