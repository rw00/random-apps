package com.rw.random.apps.benchmark;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;


public class SimpleArrayListAddBenchmark {

    public static final int ARRAY_LIST_SIZE = 100_000_000;
    public static final int WARM_UP_ITERATIONS = 4;
    public static final int MEASUREMENT_ITERATIONS = 4;

    public static void main(String[] args) throws RunnerException {
        Options benchmarkOptions = new OptionsBuilder() //
                                                        .include(SimpleArrayListAddBenchmark.class.getSimpleName()) //
                                                        .forks(1) //
                                                        .jvmArgsAppend("-Djava.io.tmpdir=target/temp") //
                                                        .build();
        Runner benchmarkRunner = new Runner(benchmarkOptions);
        benchmarkRunner.run();
    }

    @Warmup(iterations = WARM_UP_ITERATIONS)
    @Measurement(iterations = MEASUREMENT_ITERATIONS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public List<Integer> addToListWithoutInitialCapacity() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ARRAY_LIST_SIZE; i++) {
            list.add(i);
        }
        return list;
    }

    @Warmup(iterations = WARM_UP_ITERATIONS)
    @Measurement(iterations = MEASUREMENT_ITERATIONS)
    @BenchmarkMode(Mode.AverageTime)
    @Benchmark
    public List<Integer> addToListWithInitialCapacity() {
        List<Integer> list = new ArrayList<>(ARRAY_LIST_SIZE);
        for (int i = 0; i < ARRAY_LIST_SIZE; i++) {
            list.add(i);
        }
        return list;
    }
}
