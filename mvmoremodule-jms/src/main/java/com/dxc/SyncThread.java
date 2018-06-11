package com.dxc;/**
 * Created by Administrator on 2018-05-03.
 */

import java.util.concurrent.CountDownLatch;

/**
 * @author xuzhiyong
 * @createDate 2018-05-03-8:59
 * 线程发令枪 : 每次开始出后就执行await进行等待，当执行了countDown完一百次后，放开等待 全部线程同时执行
 */
public class SyncThread {
    public static void main(String agrs[]) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程" + Thread.currentThread().getId() + "开始出发");
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getId() + "已到达终点");

                }
            }).start();
            countDownLatch.countDown();
        }
    }
}
