package xzy.java8.stream;

import org.junit.Test;

import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.LongConsumer;
import java.util.function.UnaryOperator;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author xuzhiyong
 * @createDate 2019-09-05-21:47
 */
public class Java8StreamIterateTtest {

    @Test
    public void test0() {
        //无限流
        //生成1-10个数字
        Stream.iterate(1L, new UnaryOperator<Long>() {
            @Override
            public Long apply(Long aLong) {
                return aLong + 1;
            }
        }).limit(10).forEach(System.out::println);
    }


    @Test
    public void test1() {
        long n = 1000;
        //并返回从1到给定参数的所有数字的和
        //Stream.iterate创建一个无限流
        long result = Stream.iterate(1L, new UnaryOperator<Long>() {
            @Override
            public Long apply(Long aLong) {
                return aLong + 1;
            }
        }).limit(n).reduce(0L, new BinaryOperator<Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) {
                return Long.sum(aLong, aLong2);
            }
        });
        System.out.println(result);

        result = Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
        System.out.println(result);
    }

    @Test
    public void test2() {
        //上面的例子。采用并行流
        long n = 1000;
        long result = Stream.iterate(1L, new UnaryOperator<Long>() {
            @Override
            public Long apply(Long aLong) {
                return aLong + 1;
            }
        }).limit(n)
                .parallel()
                .reduce(0L, new BinaryOperator<Long>() {
                    @Override
                    public Long apply(Long aLong, Long aLong2) {
                        return Long.sum(aLong, aLong2);
                    }
                });
    }

    @Test
    public void test3() {
        //并行流内部使用了默认的 ForkJoinPool （7.2节会进一步讲到分支/合并框架），它默认的
        //线 程 数 量 就是 你 的 处 理器 数 量 ， 这个 值 是 由 Runtime.getRuntime().available-
        //Processors() 得到的
        System.out.println(Runtime.getRuntime().availableProcessors());

    }


    @Test
    public void test4() {
        //测试并行流和顺序流的性能
        measureSumPerf(Java8StreamIterateTtest::iterativeSum, 10_000_000);
        measureSumPerf(Java8StreamIterateTtest::sequentialSum, 10_000_000);
        measureSumPerf(Java8StreamIterateTtest::parallelSum, 10_000_000);
    }


    /****
     * 传入一个方法，测试10次，取用时最小的那个方法
     * @param applyFn
     * @param n
     * @return
     */
    public void measureSumPerf(Function<Long, Long> applyFn, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = applyFn.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        System.out.println("the fasttime is " + fastest);
    }

    /***
     * 串行流求1...n的总和
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L, Long::sum);
    }

    /***
     * 并行流求1...n的总和
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    /***
     * 传统方式求1..n的总和
     * @param n
     * @return
     */
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }



    @Test
    public void test5(){
        //下面代码采用并行流来计算累加，本质上是多线程的，所以会有并发问题
        //并行流的任何操作都必须是符合缩减操作的三个约束条件，无状态，不干预，关联性
        Function<Long,Long> applyFn = new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                Accumulator accumulator = new Accumulator();
                LongStream.rangeClosed(1,aLong)
                        .parallel() //加了并行流会有并发问题
                        .forEach(new LongConsumer() {
                    @Override
                    public void accept(long value) {
                        accumulator.add(value);
                    }
                });
                return accumulator.total;
            }
        };
        measureSumPerf(applyFn,10_000_000);


        //采用原子类来计算
        Function<Long,Long> applyFn2 = new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                Accumulator accumulator = new Accumulator();
                LongStream.rangeClosed(1,aLong)
                        .parallel() //加了并行流会有并发问题
                        .forEach(new LongConsumer() {
                            @Override
                            public void accept(long value) {
                                accumulator.addCAS(value);
                            }
                        });
                return accumulator.atomicLong.get();
            }
        };

        measureSumPerf(applyFn2,10_000_000);




    }
}
