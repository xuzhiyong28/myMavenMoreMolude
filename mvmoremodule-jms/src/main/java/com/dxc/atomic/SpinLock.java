package com.dxc.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xuzhiyong
 * @createDate 2020-03-02-21:14
 * 自己实现一个自旋锁
 */
public class SpinLock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    private volatile int count = 0;

    public static void main(String[] args) {
        SpinLock lock = new SpinLock();
        try{
            lock.lock();
            //todo
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void lock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            ++count;
            return;
        }
        //自旋 判断是否是null，如果是则设置为当前线程，如果不是则自旋
        while (owner.compareAndSet(null, t)) {

        }
    }
    public void unlock() {
        Thread t = Thread.currentThread();
        if (t == owner.get()) {
            if (count > 0) {
                --count;
            } else {
                owner.set(null);
            }
        }
    }
}
