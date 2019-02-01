package com.GC;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/***
 * -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:+UseSerialGC -Xloggc:D:\\Producer_gc.log
 * http://cmsblogs.com/?p=3784
 */
public class GCTest implements Runnable{

    private static ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    private Deque<byte[]> deque;
    private int objectSize;
    private int queueSize;

    public GCTest(int objectSize, int ttl) {
        this.deque = new ArrayDeque<byte[]>();
        this.objectSize = objectSize;
        this.queueSize = ttl * 1000;
    }


    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            deque.add(new byte[objectSize]);
            if (deque.size() > queueSize) {
                deque.poll();
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        executorService.scheduleAtFixedRate(
                new GCTest(200 * 1024 * 1024 / 1000, 5),
                0, 100, TimeUnit.MILLISECONDS
        );
        executorService.scheduleAtFixedRate(
                new GCTest(50 * 1024 * 1024 / 1000, 120),
                0, 100, TimeUnit.MILLISECONDS);
        TimeUnit.MINUTES.sleep(10);
        executorService.shutdownNow();
    }
}
