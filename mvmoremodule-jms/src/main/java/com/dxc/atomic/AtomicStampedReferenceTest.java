package com.dxc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xuzhiyong
 * @createDate 2020-03-01-21:40
 * CAS ABS问题的解决方案  --- 加版本号
 */
public class AtomicStampedReferenceTest {

    //当前值=1，版本号=1
    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 1);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() + ",初始值 a = " + atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            try {
                TimeUnit.SECONDS.sleep(1); //等待秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //将原来的1改成2，版本号+1，看是否成功
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程" + Thread.currentThread() + ",CAS操作结果: " + isCASSuccess);
        }, "主线程");

        Thread other = new Thread(() -> {
            Thread.yield(); // 确保thread-main 优先执行
            //将原来的1改成2，版本号+1
            atomicStampedRef.compareAndSet(1, 2, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
            //在将原来的2还原成1，版本号+1，此时发生了ABA问题
            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
        });
        main.start();
        other.start();
    }
}
