package com.dxc.atomic;/**
 * Created by Administrator on 2018-06-08.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author xuzhiyong
 * @createDate 2018-06-08-14:34
 * 原子的方式对values里面的值进行递增 采用 AtomicInteger方式
 */
public class AtomicIntegerArrayTest {
    private static int threadCount = 1000;
    private static CountDownLatch countDownLatch = new CountDownLatch(threadCount);
    public static int[] values = new int[10];
    public static AtomicIntegerArray ara = new AtomicIntegerArray(values);


    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Counter());
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        countDownLatch.await(); //当所有线程都执行成功后才开始执行下面的代码
        for (int i = 0; i < 10; i++) {
            System.out.println(ara.get(i) + " ");
        }
    }

    static class Counter implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 10; j++) {
                    ara.getAndIncrement(j); //获取值并且加1
                }
            }
            countDownLatch.countDown();
        }
    }


}
