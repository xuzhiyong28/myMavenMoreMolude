package com.dxc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Increment {
    private int i;
    private PlainLock lock = new PlainLock();

    public void increase() {
        lock.lock();
        i++;
        lock.unlock();
    }

    public int getI() {
        return i;
    }

    public static void test(int threadNum, final int loopTimes) {
        final Increment increment = new Increment();
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threads.length; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < loopTimes; i++) {
                        increment.increase();
                    }
                }
            });
            threads[i] = t;
            t.start();
        }

        for (Thread t : threads) {  //main线程等待其他线程都执行完成
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(threadNum + "个线程，循环" + loopTimes + "次结果：" + increment.getI());
    }

    public static void test2() throws InterruptedException {
        final PlainLock plainLock = new PlainLock();
        Thread.currentThread().setName("主线程");
        plainLock.lock();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                plainLock.lock();
                System.out.println("!!!!!!!!!!!!!!!!");
                plainLock.unlock();
            }
        },"子线程1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                plainLock.lock();
                System.out.println("!!!!!!!!!!!!!!!!");
                plainLock.unlock();
            }
        },"子线程2");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        plainLock.unlock();
    }


    public static void test3() throws InterruptedException {
        final ReentrantLock plainLock = new ReentrantLock();
        Thread.currentThread().setName("主线程");
        plainLock.lock();
        Thread thread = new Thread(() -> {
            plainLock.lock();
            System.out.println("!!!!!!!!!!!!!!!!");
            plainLock.unlock();
        },"子线程1");
        Thread thread2 = new Thread(() -> {
            plainLock.lock();
            System.out.println("!!!!!!!!!!!!!!!!");
            plainLock.unlock();
        },"子线程2");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        plainLock.unlock();
    }


    public static void main(String[] args) throws InterruptedException {
        test3();
    }

}
