package com.dxc.aqs;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/***
 * 根据AQS实现一个先进先出可重入锁
 */
public class PlainLock {
    private Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public static void main(String[] args) throws InterruptedException {
        int NUMBER = 100;
        PlainLock lock = new PlainLock();
        Map<String,String> myMap = Maps.newHashMap();
        for (int i = 0; i < NUMBER; i++) {
            new Thread(new Runner(lock,myMap),"线程" + i).start();
        }
        TimeUnit.SECONDS.sleep(20);
        System.out.println(myMap.entrySet().size());

    }


    private static class Runner implements Runnable {
        PlainLock lock;
        Map<String, String> myMap;

        public Runner(PlainLock plainLock, Map<String, String> myMap) {
            this.lock = plainLock;
            this.myMap = myMap;
        }

        @Override
        public void run() {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "..开始执行");
                for (int i = 0; i < 1000; i++) {
                    myMap.put(UUID.randomUUID().toString(), "1");
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }


    /****
     * 继承AQS
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            return compareAndSetState(0, 1);
        }

        @Override
        protected boolean tryRelease(int arg) {
            setState(0);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }
    }


}
