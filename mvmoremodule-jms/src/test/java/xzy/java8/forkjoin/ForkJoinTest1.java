package xzy.java8.forkjoin;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ForkJoinTest1 {
    @Test
    public void test() {
        long[] numbers = LongStream.rangeClosed(1, 10).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long result = new ForkJoinPool().invoke(task);
        System.out.println(result);
    }

    @Test
    public void test2() {
        ForkJoinPrintCalculator task = new ForkJoinPrintCalculator(0, 300);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);
        pool.shutdown();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        Integer[] array = Stream.generate(() -> RandomUtils.nextInt(0, 100)).limit(100).toArray(Integer[]::new);
        //求和看下不用forkjoin的时候值是多少
        Integer sum = Stream.of(array).reduce(0, (integer, integer2) -> integer + integer2);
        System.out.println(sum);

        ForkJoinGetSumTask task = new ForkJoinGetSumTask(array, 0, array.length);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        Future<Integer> future = pool.submit(task); //提交分解的SumTask 任务
        System.out.println("多线程执行结果：" + future.get());
        pool.shutdown(); //关闭线程池
    }

    @Test
    public void test4() {
        int MAX = 10000;
        int inits[] = new int[MAX];
        for (int i = 0; i < inits.length; i++) {
            inits[i] = RandomUtils.nextInt(0, MAX);
        }
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinArraySortTask task = new ForkJoinArraySortTask(inits);
        int[] result = pool.invoke(task);
        //打印
        Arrays.stream(result).forEach(System.out::println);

    }
}
