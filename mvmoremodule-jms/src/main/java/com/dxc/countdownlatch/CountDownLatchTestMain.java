package com.dxc.countdownlatch;/**
 * Created by Administrator on 2018-06-01.
 */

import java.util.concurrent.CountDownLatch;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-13:56
 */
public class CountDownLatchTestMain {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker worker0 = new Worker("worker0", (long) (Math.random() * 2000 + 3000), countDownLatch);
        Worker worker1 = new Worker("worker1", (long) (Math.random() * 2000 + 3000), countDownLatch);
        Worker worker2 = new Worker("worker2", (long) (Math.random() * 2000 + 3000), countDownLatch);
        worker0.start();
        worker1.start();
        countDownLatch.await();
        worker2.start(); //worker2必须等到worker1,worker2好了才会开始工作
    }

    static class Worker extends Thread {
        private String name;
        private long time;
        private CountDownLatch countDownLatch;

        public Worker(String name, long time, CountDownLatch countDownLatch) {
            this.name = name;
            this.time = time;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + "开始工作");
                Thread.sleep(time);
                System.out.println(name + "第一阶段工作完成");
                countDownLatch.countDown(); //减1
                Thread.sleep(2000); //这里就姑且假设第二阶段工作都是要2秒完成
                System.out.println(name + "第二阶段工作完成");
                System.out.println(name + "工作完成，耗费时间=" + (time + 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
