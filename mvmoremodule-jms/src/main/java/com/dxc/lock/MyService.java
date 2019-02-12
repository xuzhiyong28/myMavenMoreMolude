package com.dxc.lock;/**
 * Created by Administrator on 2018-06-01.
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-8:45
 */
public class MyService {
    private Lock lock = new ReentrantLock();
    public void testMethod1() {
        try {
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getId() + "-" + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
