package xzy.java8.newFuture;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/***
 * https://www.jianshu.com/p/6bac52527ca4
 */
public class Test {

    /***
     *  whenComplete 和 whenCompleteAsync 的区别：
     whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
     whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行
     */
    @org.junit.Test
    public void testWhenComplete() throws InterruptedException, ExecutionException {
        //whenComplete是任务执行完后做的操作
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===执行中===");
            }
        }).whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("任务执行完后继续执行该任务");
            }
        }).whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("任务执行完后再将任务放到线程池执行");
            }
        });
        future.get();
        ReentrantLock r = new ReentrantLock();
        r.lockInterruptibly();
    }

    /***
     * 第二个任务依赖第一个任务的结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @org.junit.Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        //如果第一个任务出现异常，thenApply将不会执行
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = RandomUtils.nextLong(0, 100);
                System.out.println("result1=" + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                long result = aLong * 5;
                System.out.println("result2=" + result);
                return result;
            }
        });
        long result = future.get();
        System.out.println(result);
    }

    @org.junit.Test
    public void testHandle() throws ExecutionException, InterruptedException {
        /***
         *  handle 是执行任务完成时对结果的处理。
         handle 方法和 thenApply 方法处理方式基本一样。不同的是 handle 是在任务完成后再执行，
         还可以处理异常的任务。thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法
         */
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new RandomUtils().nextInt(0, 10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer integer, Throwable throwable) {
                System.out.println("=========handle==========");
                int result = -1;
                if (throwable == null) {
                    result = integer * 2;
                } else {
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        Integer result = future.get();
        System.out.println(result);
    }

    @org.junit.Test
    public void testHandleMuit() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Integer> intList = Lists.newArrayList();
        IntStream.range(0, 10000).forEach(intList::add);
        List<CompletableFuture<Integer>> completaList = Lists.newArrayList();
        BiFunction<Integer, Throwable, Integer> biFunction = new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer integer, Throwable throwable) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return integer * 2;
            }
        };
        for (int i = 0; i < intList.size(); i++) {
            int ii = i;
            completaList.add(CompletableFuture.supplyAsync(new Supplier<Integer>() {
                @Override
                public Integer get() {
                    return intList.get(ii);
                }
            }, executorService).handle(biFunction));
        }

        for (CompletableFuture<Integer> future : completaList) {
            System.out.println(future.get());
        }
    }


    /***
     * thenAccept 接收任务的处理结果，并消费处理，无返回结果
     * 从示例代码中可以看出，该方法只是消费执行完成的任务，并可以根据上面的任务返回的结果进行处理。并没有后续的输错操作
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @org.junit.Test
    public void testThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();
    }

    /***
     * thenCombine 合并任务
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理
     */
    @org.junit.Test
    public void testThenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "world";
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + " " + s2;
            }
        });
        System.out.println(result.get());
    }

    @org.junit.Test
    public void testJoin() throws ExecutionException, InterruptedException {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f1";
        });
        System.out.println("f1 done");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";
        });
        System.out.println("f2 done");
        CompletableFuture<String> f3 = f2.thenCombine(f1, (s, s2) -> {
            System.out.println(s);
            System.out.println(s2);
            return s + ".." + s2;
        });
        long startTime = System.currentTimeMillis();
        System.out.println(f3.join());
        System.out.println(System.currentTimeMillis() - startTime + " ms");
    }


    @org.junit.Test
    public void testJoinOr() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f1";
        });
        System.out.println("f1 done");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";
        });
        System.out.println("f2 done");
        CompletableFuture<String> f3 = f2.applyToEither(f1, new Function<String, String>() {
            @Override
            public String apply(String s) {
                return f1.join() + "..." + s;
            }
        });
        System.out.println(f3.join());
    }


    @org.junit.Test
    public void testAcceptBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("=====f1=====");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });
        CompletableFuture<Void> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("=====f2=====");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

        //CompletableFuture<Void> f3 = f1.thenAcceptBoth(f2, (aVoid, aVoid2) -> System.out.println("===f3==="));
        //f3.get();
        CompletableFuture<Void> f4 = f2.thenAcceptBoth(f1, (aVoid, aVoid2) -> System.out.println("===f3==="));
        f4.get();
    }

    @org.junit.Test
    public void testAllOf() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<CompletableFuture<String>> futureList = Lists.newArrayList();
        List<String> uuidList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            futureList.add(CompletableFuture.supplyAsync(() -> {
                synchronized (uuidList) {
                    for (int j = 0; j < 100; j++) {
                        uuidList.add(UUID.randomUUID().toString());
                    }
                }
                System.out.println("threadID:" + Thread.currentThread().getId() + "执行完成");
                return null;
            }, executorService));
        }
        CompletableFuture<Void> result = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        result.get();
        System.out.println(uuidList.size());
    }

    @org.junit.Test
    public void testxx() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        byte[] o = new byte[0];
        List<String> list = Lists.newArrayList();
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                /*synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }*/
                list.add(UUID.randomUUID().toString());

            }
            return "f1";
        },executorService);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
               /*synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }*/
                list.add(UUID.randomUUID().toString());

            }
            return "f2";
        },executorService);
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                /*synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }*/
                list.add(UUID.randomUUID().toString());

            }
            return "f2";
        },executorService);
        CompletableFuture<String> f4 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }

            }
            return "f2";
        },executorService);
        CompletableFuture<String> f5 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                /*synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }*/
                list.add(UUID.randomUUID().toString());

            }
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";
        },executorService);
        CompletableFuture<Void> anyResult = CompletableFuture.allOf(f1, f2, f3, f4, f5);
        long startTime = System.currentTimeMillis();
        anyResult.get();
        System.out.println(System.currentTimeMillis() - startTime);
    }


}
