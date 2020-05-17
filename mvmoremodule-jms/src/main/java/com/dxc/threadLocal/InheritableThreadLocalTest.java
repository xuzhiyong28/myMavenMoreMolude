package com.dxc.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.*;

public class InheritableThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<String> alibabaThreadLocal = new TransmittableThreadLocal<>();
    public static void main(String args[]) throws Exception {
        test2_2();
    }


    /***
     * 测试InheritableThreadLocal 父子线程传递性
     */
    public static void test1() throws InterruptedException {
        threadLocal.set("许志勇");
        System.out.println(String.format("主线程获取到threadlocal值 = %s",threadLocal.get()));
        Thread myThead = new Thread(() ->
                System.out.println(String.format("子线程获取到threadlocal值 = %s",threadLocal.get())));
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
        ExecutorService taskService = Executors.newFixedThreadPool(1000);
        ExecutorService logService = Executors.newFixedThreadPool(1000);
        for(int i = 0 ; i < TASK_COUNT ; i++ ){
            int TASK_ID = i;
            Future<String> future = taskService.submit(() -> {
                inheritableThreadLocal.set("TASK_" + TASK_ID);
                logService.execute(() -> System.out.println(inheritableThreadLocal.get() + "_" + TASK_ID + "_日志A"));
                logService.execute(() -> System.out.println(inheritableThreadLocal.get() + "_" + TASK_ID + "_日志B"));
                logService.execute(() -> System.out.println(inheritableThreadLocal.get() + "_" + TASK_ID + "_日志C"));
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
    public static void test2_2() throws ExecutionException, InterruptedException{
        ThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();
        int TASK_COUNT = 10;
        ExecutorService taskService = Executors.newFixedThreadPool(2);
        ExecutorService logService = Executors.newFixedThreadPool(2);
        for(int i = 0 ; i < TASK_COUNT ; i++ ){
            int TASK_ID = i;
            Future<String> future = taskService.submit(() -> {
                transmittableThreadLocal.set("TASK_" + TASK_ID);
                logService.execute(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志A")));
                logService.execute(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志B")));
                logService.execute(TtlRunnable.get(() -> System.out.println(transmittableThreadLocal.get() + "_" + TASK_ID + "_日志C")));
                return null;
            });
            future.get();
        }
        TimeUnit.SECONDS.sleep(10);
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


}
