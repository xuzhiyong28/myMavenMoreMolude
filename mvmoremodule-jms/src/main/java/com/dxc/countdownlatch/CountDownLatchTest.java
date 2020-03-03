package com.dxc.countdownlatch;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2020-03-03-21:23
 *
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int NUMBER = 100;
        Map<String, String> myMap = Maps.newHashMap();
        CountDownLatch downLatch = new CountDownLatch(2);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                downLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                downLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < NUMBER; i++) {
            new Thread(new Runner(downLatch,myMap)).start();
        }
        System.out.println("线程设置成功");
        TimeUnit.SECONDS.sleep(10);
        System.out.println(myMap.entrySet().size());

    }

    static class Runner implements Runnable {
        private CountDownLatch countDownLatch;
        private Map<String, String> myMap;

        public Runner(CountDownLatch countDownLatch, Map<String, String> myMap) {
            this.countDownLatch = countDownLatch;
            this.myMap = myMap;
        }

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < 1000; j++) {
                myMap.put(UUID.randomUUID().toString(), "1");
            }
        }
    }
}
