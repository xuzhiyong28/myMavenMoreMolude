package com.dxc.util;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureTaskScheduler extends Thread {
    private ConcurrentLinkedQueue<Runnable> executeTaskQueue = new ConcurrentLinkedQueue<Runnable>();// 任务队列
    private long sleepTime = 200;// 线程休眠时间
    private ExecutorService pool = Executors.newFixedThreadPool(10);
    private static FutureTaskScheduler inst = new FutureTaskScheduler();

    private FutureTaskScheduler() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            handleTask();// 处理任务
            threadSleep(sleepTime);
        }
    }

    /**
     * 添加任务
     *
     * @param executeTask
     */
    public static void add(Runnable executeTask) {
        inst.executeTaskQueue.add(executeTask);
    }

    private void threadSleep(long time) {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * 处理任务队列，检查其中是否有任务
     */
    private void handleTask() {
        Runnable executeTask;
        while (executeTaskQueue.peek() != null) {
            executeTask = executeTaskQueue.poll();
            handleTask(executeTask);
        }
    }

    private void handleTask(Runnable executeTask) {
        pool.execute(executeTask);
    }


}
