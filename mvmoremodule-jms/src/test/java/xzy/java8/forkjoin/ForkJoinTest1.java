package xzy.java8.forkjoin;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ForkJoinTest1 {
    @Test
    public void test() {
        long[] numbers = LongStream.rangeClosed(1, 5263).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        long start = System.currentTimeMillis();
        long result = new ForkJoinPool().invoke(task);
        System.out.println("结果 = " + result  + ", 耗时 = " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        long otherResult = 0;
        for (long number : numbers) {
            otherResult += number;
        }
        System.out.println("结果 = " + otherResult  + ", 耗时 = " + (System.currentTimeMillis() - start) + " ms");
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


    /****
     * 采用fork/join对数组进行排序
     */
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

    @Test
    public void test5() throws InterruptedException {
        Map<String, Integer> myMap = Maps.newHashMap();
        for (int i = 0; i < 100000; i++) {
            myMap.put(String.valueOf(i), 1);
        }
        ForkJoinPool pool = new ForkJoinPool();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int j = 0 ; j < 10 ; j++){
            executorService.execute(() -> {
                ForkJoinMapDoTask forkJoinMapDoTask = new ForkJoinMapDoTask(myMap);
                System.out.println("核心线程数量:" + pool.getPoolSize());
                System.out.println("getQueuedTaskCount:" + pool.getQueuedTaskCount());
                Map<String, Integer> invoke = pool.invoke(forkJoinMapDoTask);
                System.out.println("最后结果长度:" + invoke.keySet().size());
            });
        }
        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void test6(){
        Map<String, Integer> myMap = Maps.newHashMap();
        for (int i = 0; i < 526252; i++) {
            myMap.put(String.valueOf(i), 1);
        }
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinMapDoTask forkJoinMapDoTask = new ForkJoinMapDoTask(myMap);
        Map<String, Integer> invoke = pool.invoke(forkJoinMapDoTask);
        System.out.println(invoke.keySet().size());
        for(Map.Entry<String,Integer> entry : invoke.entrySet()){
            if(entry.getValue() != 11){
                System.out.println("不等于");
            }
        }
    }
}
