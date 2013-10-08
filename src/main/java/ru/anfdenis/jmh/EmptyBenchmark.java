package ru.anfdenis.jmh;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.GenerateMicroBenchmark;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

/**
 * Denis Anfertev
 * 10/8/13 5:04 PM
 */
public class EmptyBenchmark {
    @GenerateMicroBenchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(1)
    @Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.NANOSECONDS)
    @Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.NANOSECONDS)
    public void testToBuffer() {
    }
}
