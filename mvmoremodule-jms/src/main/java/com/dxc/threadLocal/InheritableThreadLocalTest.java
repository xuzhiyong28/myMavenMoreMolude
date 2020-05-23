package com.dxc.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;
import com.alibaba.ttl.TtlRunnable;
import com.google.common.collect.Lists;
import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class InheritableThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<String> alibabaThreadLocal = new TransmittableThreadLocal<>();

    public static void main(String args[]) throws Exception {
        test2_4();
    }


    /***
     * 测试InheritableThreadLocal 父子线程传递性
     */
    public static void test1() throws InterruptedException {
        threadLocal.set("许志勇");
        System.out.println(String.format("主线程获取到threadlocal值 = %s", threadLocal.get()));
        Thread myThead = new Thread(() ->
                System.out.println(String.format("子线程获取到threadlocal值 = %s", threadLocal.get())));
        myThead.start();
        myThead.join();
    }

    /***
     * 测试 InheritableThreadLocal 线程池不可传递性
     * InheritableThreadLocal和线程池搭配使用时，可能得不到想要的结果，因为线程池中的线程是复用的，
     * 并没有重新初始化线程，InheritableThreadLocal之所以起作用是因为在Thread类中最终会调用init()方法去把InheritableThreadLocal的map复制到子线程中。
     * 由于线程池复用了已有线程，所以没有调用init()方法这个过程，也就不能将父线程中的InheritableThreadLocal值传给子线程
     */
    public static void test2_1() throws ExecutionException, InterruptedException {
        ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        int TASK_COUNT = 10;
        ExecutorService taskService = Executors.newFixedThreadPool(2);
        ExecutorService logService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < TASK_COUNT; i++) {
            int TASK_ID = i;
            Future<String> future = taskService.submit(() -> {
                inheritableThreadLocal.set("TASK_" + TASK_ID);
                logService.execute(() -> System.out.println(inheritableThreadLocal.get() + "_" + TASK_ID + "_日志A"));
                logService.execute(() -> System.out.println(inheritableThreadLocal.get() + "_" + TASK_ID + "_日志B"));
                logService.execute(() -> System.out.println(inheritableThreadLocal.get() + "_" + TASK_ID + "_日志C"));
                inheritableThreadLocal.remove();
                return null;
            });
            future.get();
            TimeUnit.SECONDS.sleep(1);
        }
        taskService.shutdown();
    }

    /***
     * 采用TransmittableThreadLocal解决
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test2_2() throws ExecutionException, InterruptedException {
        ThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();
        int TASK_COUNT = 10;
        ExecutorService taskService = Executors.newFixedThreadPool(2);
        ExecutorService logService = Executors.newFixedThreadPool(2);
        ExecutorService futureService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < TASK_COUNT; i++) {
            int TASK_ID = i;
            Future<String> future = taskService.submit(() -> {
                transmittableThreadLocal.set("TASK_" + TASK_ID);
                logService.execute(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志A，时间 =" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))));
                logService.execute(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志B，时间 =" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))));
                logService.execute(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志C，时间 =" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"))));
                logService.execute(TtlRunnable.get(() -> {
                    System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志D");
                    List<CompletableFuture<Void>> futureList = Lists.newArrayList();
                    int futureCount = 10;
                    for (int j = 0; j < futureCount; j++) {
                        futureList.add(CompletableFuture.runAsync(TtlRunnable.get(() -> {
                            System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志D_D");
                        }), futureService));
                        //futureList.add(CompletableFuture.runAsync(new RunTask(TASK_ID,transmittableThreadLocal.get()),futureService));
                    }
                    CompletableFuture<Void> resultFuture = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
                    try {
                        resultFuture.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }));
                return null;
            });
            future.get();
        }
        TimeUnit.SECONDS.sleep(10);
    }

    public static void test2_3() throws Exception {
        ExecutorService taskService = Executors.newFixedThreadPool(2);
        ExecutorService logService = Executors.newFixedThreadPool(2);
        int TASK_COUNT = 10;
        for (int i = 0; i < TASK_COUNT; i++) {
            /*Future<Void> future = taskService.submit(() -> {
                Long threadId = Thread.currentThread().getId();
                UuidHelper.addQueue(threadId + "_日志1");
                UuidHelper.addQueue(threadId + "_日志2");
                UuidHelper.addQueue(threadId + "_日志3");
                UuidHelper.remove();
                return null;
            });
            future.get();*/
            Future<Void> future2 = taskService.submit(TtlCallable.get(() -> {
                Long threadId = Thread.currentThread().getId();
                UuidHelper.addQueue(threadId + "_日志1");
                UuidHelper.addQueue(threadId + "_日志2");
                UuidHelper.addQueue(threadId + "_日志3");
                UuidHelper.remove();
                return null;
            }));
            future2.get();


        }
        TimeUnit.SECONDS.sleep(20);
        taskService.shutdownNow();
    }


    /***
     * 多层嵌套 线程池里面在执行线程池
     * @throws Exception
     */
    public static void test2_4() throws Exception {
        ExecutorService taskService = Executors.newFixedThreadPool(2);
        ExecutorService logService = Executors.newFixedThreadPool(2);
        int TASK_COUNT = 10;
        for (int i = 0; i < TASK_COUNT; i++) {
            final int TASK_ID = i;
            taskService.execute(TtlRunnable.get(() -> {
                UuidHelper.get();
                System.out.println(TASK_ID + "_" + UuidHelper.get());
                Future<Void> future2 = logService.submit(TtlCallable.get(() -> {
                    UuidHelper.addQueue("_日志1");
                    UuidHelper.addQueue("_日志2");
                    UuidHelper.addQueue("_日志3");
                    FutureTask<Void> task = new FutureTask<>(new Callable<Void>() {
                        @Override
                        public Void call() throws Exception {
                            UuidHelper.addQueue("_日志4");
                            UuidHelper.addQueue("_日志5");
                            UuidHelper.addQueue("_日志6");
                            return null;
                        }
                    });
                    task.run();
                    task.get();
                    UuidHelper.addQueue("_日志7");
                    UuidHelper.addQueue("_日志8");
                    //UuidHelper.remove();
                    return null;
                }));
                try {
                    future2.get();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }));

        }
        TimeUnit.SECONDS.sleep(20);
        taskService.shutdownNow();
    }

    /***
     * 测试 InheritableThreadLocal 线程池不可传递性
     *
     * InheritableThreadLocal和线程池搭配使用时，可能得不到想要的结果，因为线程池中的线程是复用的，
     * 并没有重新初始化线程，InheritableThreadLocal之所以起作用是因为在Thread类中最终会调用init()方法去把InheritableThreadLocal的map复制到子线程中。
     * 由于线程池复用了已有线程，所以没有调用init()方法这个过程，也就不能将父线程中的InheritableThreadLocal值传给子线程
     */
    public static void test3_1() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("----主线程启动");
        threadLocal.set("主线程第一次赋值");
        executorService.submit(() -> System.out.println("----子线程获取值：" + threadLocal.get()));
        System.out.println("主线程休眠2秒");
        Thread.sleep(2000);
        threadLocal.set("主线程第二次赋值");
        executorService.submit(() -> System.out.println("----子线程获取值：" + threadLocal.get()));
        executorService.shutdown();
    }

    /**
     * 使用TransmittableThreadLocal
     *
     * @throws InterruptedException
     */
    public static void test3_2() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("----主线程启动");
        alibabaThreadLocal.set("主线程第一次赋值");
        executorService.submit(TtlRunnable.get(() -> System.out.println("----子线程获取值：" + alibabaThreadLocal.get())));
        System.out.println("主线程休眠2秒");
        Thread.sleep(2000);
        alibabaThreadLocal.set("主线程第二次赋值");
        executorService.submit(TtlRunnable.get(() -> System.out.println("----子线程获取值：" + alibabaThreadLocal.get())));
        executorService.shutdown();
    }

    static class RunTask implements Runnable {
        Integer TASK_ID;
        String content;

        public RunTask(Integer TASK_ID, String content) {
            this.TASK_ID = TASK_ID;
            this.content = content;
        }

        @Override
        public void run() {
            System.out.println(TASK_ID + "_" + content);
        }
    }


}
