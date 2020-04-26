package com.dxc.util;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackTaskScheduler extends Thread {

    private ConcurrentLinkedQueue<CallbackTask> executeTaskQueue = new ConcurrentLinkedQueue<>();

    private long sleepTime = 200; //线程休眠时间

    private ExecutorService jPool = Executors.newCachedThreadPool();

    //用guava的类装饰线程池
    ListeningExecutorService gPool = MoreExecutors.listeningDecorator(jPool);

    private static CallbackTaskScheduler inst = new CallbackTaskScheduler();

    private CallbackTaskScheduler(){
        this.start();
    }


    /***
     * 添加任务
     * @param executeTask
     * @param <R>
     */
    public static <R> void add(CallbackTask<R> executeTask){
        inst.executeTaskQueue.add(executeTask);
    }



    @Override
    public void run() {
        while (true){
            handleTask();
            threadSleep(sleepTime);
        }
    }

    /***
     * 休眠指定时间
     * @param time
     */
    private void threadSleep(long time) {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void handleTask(){
        CallbackTask executeTask = null;
        while (executeTaskQueue.peek() != null){
            executeTask = executeTaskQueue.poll();
            handleTask(executeTask);
        }
    }

    /***
     * 执行任务操作
     * @param executeTask
     * @param <R>
     */
    private <R> void handleTask(CallbackTask<R> executeTask){
        ListenableFuture<R> future = gPool.submit(() -> executeTask.execute());
        Futures.addCallback(future, new FutureCallback<R>() {
            @Override
            public void onSuccess(R r) {
                executeTask.OnBack(r);
            }

            @Override
            public void onFailure(Throwable throwable) {
                executeTask.onException(throwable);
            }
        });
    }



}
