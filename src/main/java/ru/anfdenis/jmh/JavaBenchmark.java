package ru.anfdenis.jmh;

import org.openjdk.jmh.annotations.BenchmarkType;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Measurement;

import java.util.ArrayList;
import java.util.List;

/**
 * Denis Anfertev
 * 16.05.13 17:05
 */
public class JavaBenchmark {
    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 5)
    public void wrapper() {
        ScalaBenchmark.test();
    }

    @GenerateMicroBenchmark(BenchmarkType.AverageTimePerOp)
    @Measurement(iterations = 5)
    public void test() {
        List<Integer> list = new ArrayList<Integer>(1000000);
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
    }
}
