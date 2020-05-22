package com.dxc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/***
 * 多个CountDownLatch 同时使用
 */
public class MutlCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int TASK_COUNT = 10;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(TASK_COUNT);
        for(int i = 0 ; i < TASK_COUNT ; i++){
            new Thread(new Worker(startSignal,doneSignal)).start();
        }
        System.out.println("first");
        startSignal.countDown(); // 这里执行countDown后， 任务线程同一时间全部执行
        doneSignal.await(); //这里await 保证 任务线程全部执行成功后才执行下面的内容
        System.out.println("last");
    }

    static class Worker implements Runnable {
        private final CountDownLatch startSignal;
        private final CountDownLatch doneSignal;
        Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            // 为了让所有线程同时开始任务，我们让所有线程先阻塞在这里
            // 等大家都准备好了，再打开这个门栓
            try {
                startSignal.await();
                System.out.println("do work," + Thread.currentThread().getId());
                doneSignal.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
