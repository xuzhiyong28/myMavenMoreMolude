package com.dxc.aqs;

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

    public static void test(int threadNum, int loopTimes) {
        Increment increment = new Increment();
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

    public static void main(String[] args){
        //test(20, 1);
        PlainLock plainLock = new PlainLock();
        plainLock.lock();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                plainLock.lock();
                System.out.println("!!!!!!!!!!!!!!!!");
                plainLock.unlock();
            }
        },"子线程");
        thread.start();
        plainLock.unlock();
    }

}
