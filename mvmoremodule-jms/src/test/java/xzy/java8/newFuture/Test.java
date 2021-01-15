package xzy.java8.newFuture;


import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.*;
import java.util.stream.IntStream;

/***
 * https://www.jianshu.com/p/6bac52527ca4
 */
public class Test {


    @org.junit.Test
    public void runOrsupply() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("!!!");
            }
        }, Executors.newCachedThreadPool());

        voidCompletableFuture.get();

        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "success";
            }
        });
        System.out.println(stringCompletableFuture.get());
    }


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
                    System.out.println("run线程-" + Thread.currentThread().getId());
                    TimeUnit.SECONDS.sleep(1);
                    int i = 1 / 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, Executors.newCachedThreadPool()).whenComplete(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("whenComplete线程-" + Thread.currentThread().getId());
                System.out.println("任务执行完后继续执行该任务");
            }
        }).whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            @Override
            public void accept(Void aVoid, Throwable throwable) {
                System.out.println("whenCompleteAsync线程-" + Thread.currentThread().getId());
                System.out.println("任务执行完后再将任务放到线程池执行");
            }
        }).exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                return null;
            }
        });
        future.get();
    }

    @org.junit.Test
    public void testWhenComplete2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                int i = 1 / 0;
                return "xuzy";
            }
        }).whenComplete(new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                System.out.println("获取supplyAsync返回的值 = " + s);
            }
        }).exceptionally(new Function<Throwable, String>() {
            @Override
            public String apply(Throwable throwable) {
                //当supplyAsync失败时，捕获异常并返回值
                return "xuzy is fail";
            }
        });
        System.out.println(stringCompletableFuture.get());
    }

    /***
     * 第二个任务依赖第一个任务的结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @org.junit.Test
    public void testThenApply() throws ExecutionException, InterruptedException {
        //任务A
        CompletableFuture<Long> futureA = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                return RandomUtils.nextLong(0, 100);
            }
        });
        //任务B
        CompletableFuture<String> futureB = futureA.thenApply(new Function<Long, String>() {
            @Override
            public String apply(Long aLong) {
                long result = aLong * 5;
                return "xuzy-" + result;
            }
        });
        System.out.println(futureB.get());
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
                int i = 1 / 0;
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
        CompletableFuture<Integer> futureA = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("任务A执行");
                return new Random().nextInt(10);
            }
        });
        CompletableFuture<Void> futureVoid = futureA.thenAccept(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("获取到任务A的结果 ：" + integer + " , 并消费");
            }
        });
        futureVoid.get();


        futureA.thenRun(new Runnable() {
            @Override
            public void run() {

            }
        });


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
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello";
            }
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "world";
            }
        });
        CompletableFuture<String> result = future1.thenCombine(future2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + " " + s2;
            }
        });
        long start = System.currentTimeMillis();
        System.out.println(result.get());
        System.out.println((System.currentTimeMillis() - start) + " ms");

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
            System.out.println("任务f1");
            return "f1";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";
        });
        //这里f1.applyToEither(f2  f1在前面还是f2在前面都无所谓
        CompletableFuture<String> f3 = f1.applyToEither(f2, new Function<String, String>() {
            @Override
            public String apply(String s) {
                return "最终是哪个任务的值呢 ？ 值是 : " + s + ", 获取后可以继续对值做处理";
            }
        });
        long start = System.currentTimeMillis();
        System.out.println(f3.join());
        System.out.println("耗时:" + (System.currentTimeMillis() - start) + " ms");
        try {
            //这里阻塞20秒发现f1的任务还会继续执行，只是在结果上先取了f2的数据
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testrunAfterEither() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务f1");
            return "f1";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";
        });
        CompletableFuture<Void> f3 = f1.runAfterEither(f2, new Runnable() {
            @Override
            public void run() {
                System.out.println("f3");
            }
        });
        long start = System.currentTimeMillis();
        f3.join();
        System.out.println("耗时:" + (System.currentTimeMillis() - start) + " ms");
        try {
            //这里阻塞20秒发现f1的任务还会继续执行，只是在结果上先取了f2的数据
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void testrunAfterBoth() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f1";
        });
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "f2";
        });
        CompletableFuture<Void> f3 = f1.runAfterBoth(f2, new Runnable() {
            @Override
            public void run() {
                System.out.println("f1,f2都执行成功后执行");
            }
        });
        long start = System.currentTimeMillis();
        f3.join();
        System.out.println("耗时:" + (System.currentTimeMillis() - start) + " ms");
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
    public void testAllOfAndDoSome() throws ExecutionException, InterruptedException {



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
        }, executorService);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
               /*synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }*/
                list.add(UUID.randomUUID().toString());

            }
            return "f2";
        }, executorService);
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                /*synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }*/
                list.add(UUID.randomUUID().toString());

            }
            return "f2";
        }, executorService);
        CompletableFuture<String> f4 = CompletableFuture.supplyAsync(() -> {
            for (int i = 0; i < 100000; i++) {
                synchronized (o) {
                    list.add(UUID.randomUUID().toString());
                }

            }
            return "f2";
        }, executorService);
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
        }, executorService);
        CompletableFuture<Void> anyResult = CompletableFuture.allOf(f1, f2, f3, f4, f5);
        long startTime = System.currentTimeMillis();
        anyResult.get();
        System.out.println(System.currentTimeMillis() - startTime);
    }


    @org.junit.Test
    public void testthenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                System.out.println("t1=" + t);
                return t;
            }
        }).thenCompose(new Function<Integer, CompletionStage<Integer>>() {
            @Override
            public CompletionStage<Integer> apply(Integer param) {
                return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int t = param * 2;
                        System.out.println("t2=" + t);
                        return t;
                    }
                });
            }

        });
        System.out.println("thenCompose result : " + f.get());
    }

    @org.junit.Test
    public void testAnyOf() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f1 done");
                return 1;
            }
        });
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f2 done");
                return 2;
            }
        });
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                try {
                    TimeUnit.SECONDS.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("f3 done");
                return 3;
            }
        });

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(f1, f2, f3);
        long start = System.currentTimeMillis();
        Object o = objectCompletableFuture.get();
        System.out.println("result = " + o + " 消耗时间:" + (System.currentTimeMillis() - start) + " ms");
        TimeUnit.SECONDS.sleep(30);
    }


    @org.junit.Test
    public void getOrJoin(){
        CompletableFuture<Void> f = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("do");
                int i = 1 / 0;
            }
        });
        try {
            f.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        f.join();
    }



}
