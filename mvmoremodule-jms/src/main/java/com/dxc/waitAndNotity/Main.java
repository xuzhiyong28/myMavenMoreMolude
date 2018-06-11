package com.dxc.waitAndNotity;/**
 * Created by Administrator on 2018-05-31.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-31-18:40
 */
public class Main {
    public static void main(String agrs[]) throws InterruptedException {
        Object lock = new Object();
        MyThread1 myThread1 = new MyThread1(lock);
        myThread1.start();
        Thread.sleep(20000);
        MyThread2 myThread2 = new MyThread2(lock);
        myThread2.start();

    }

    static class MyThread1 extends Thread{
        private Object lock;
        public MyThread1(Object lock){
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("开始1 ===" + Thread.currentThread().getId());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("结束1 ===" + Thread.currentThread().getId());
            }
        }
    }

    static class MyThread2 extends Thread{
        private Object lock;
        public MyThread2(Object lock){
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized (lock){
                System.out.println("开始2 ===" + Thread.currentThread().getId());
                lock.notify();
                System.out.println("结束2 ===" + Thread.currentThread().getId());
            }
        }
    }
}
