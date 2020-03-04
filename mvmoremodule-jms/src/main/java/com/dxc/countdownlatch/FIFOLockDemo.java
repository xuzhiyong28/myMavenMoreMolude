package com.dxc.countdownlatch;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/***
 * 先进先出不可重入锁
 */
public class FIFOLockDemo {
    private final AtomicBoolean locked = new AtomicBoolean(false);

    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        FIFOLockDemo lockDemo = new FIFOLockDemo();
        new Thread(new Runner(lockDemo)).start();
        TimeUnit.SECONDS.sleep(5);
        lockDemo.lock();
        lockDemo.unlock();
    }


    public void lock() {
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);
        // 只有自己在队首才可以获得锁，否则阻塞自己
        //cas 操作失败的话说明这里有并发，别人已经捷足先登了，那么也要阻塞自己的
        //有了waiters.peek() != current判断如果自己队首了，为什么不直接获取到锁还要cas 操作呢？
        //主要是因为接下来那个remove 操作把自己移除掉了额，但是他还没有真正释放锁，锁的释放在unlock方法中释放的
        while (waiters.peek() != current ||
                !locked.compareAndSet(false, true)) {
            //这里就是使用LockSupport 来阻塞当前线程
            LockSupport.park(this);
            //这里的意思就是忽略线程中断，只是记录下曾经被中断过
            //大家注意这里的java 中的中断仅仅是一个状态，要不要退出程序或者抛异常需要程序员来控制的
            if (Thread.interrupted()) {
                wasInterrupted = true;
            }
        }
        // 移出队列，注意这里移出后，后面的线程就处于队首了，但是还是不能获取到锁的，locked 的值还是true,
        // 上面while 循环的中的cas 操作还是会失败进入阻塞的
        waiters.remove();
        //如果被中断过，那么设置中断状态
        if (wasInterrupted) {
            current.interrupt();
        }

    }

    public void unlock() {
        locked.set(false);
        //唤醒位于队首的线程
        LockSupport.unpark(waiters.peek());
    }

    static class Runner implements Runnable{
        FIFOLockDemo fifoLockDemo;
        public Runner(FIFOLockDemo fifoLockDemo){
            this.fifoLockDemo = fifoLockDemo;
        }
        @Override
        public void run() {
            fifoLockDemo.lock();
            System.out.println("===子线程执行===");
            try {
                TimeUnit.SECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fifoLockDemo.unlock();
        }
    }
}
