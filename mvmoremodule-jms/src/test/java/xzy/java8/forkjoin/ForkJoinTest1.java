package xzy.java8.forkjoin;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest1 {
    @Test
    public void test(){
        long[] numbers = LongStream.rangeClosed(1,10).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long result = new ForkJoinPool().invoke(task);
        System.out.println(result);
    }
}
