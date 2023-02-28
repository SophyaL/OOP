package ru.nsu.fit.lylova;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Benchmark for checker {@code ParallelCheckerWithThreads}.
 */
public class BenchmarkThreadsCount {
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 3)
    @Measurement(iterations = 1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @org.openjdk.jmh.annotations.Benchmark
    public boolean parallelThread(BenchmarkThreadsCount.BenchmarkState state) {
        CheckerOfArrayForNonSimpleNumbers finder = new ParallelCheckerWithThreads(
                state.threadsCount
        );
        return finder.hasNonPrimeNumber(state.bigPrimeNumbers);
    }

    /**
     * Class with state of benchmark.
     */
    @State(Scope.Benchmark)
    public static class BenchmarkState {

        @Param({"1", "2", "4", "6", "8", "12", "16", "24", "32", "40", "50"})//
        public int threadsCount;
        public int numberOfPrimeNumbers = 50000;
        public int[] bigPrimeNumbers;

        /**
         * Fill array {@code bigPrimeNumbers} with prime numbers.
         */
        @Setup(Level.Trial)
        public void setUp() {
            bigPrimeNumbers = new int[numberOfPrimeNumbers];
            for (int i = 0; i < numberOfPrimeNumbers; i++) {
                if (i < numberOfPrimeNumbers / 2) {
                    bigPrimeNumbers[i] = 1000000007;
                } else {
                    bigPrimeNumbers[i] = 998244353;
                }
            }
        }
    }
}
