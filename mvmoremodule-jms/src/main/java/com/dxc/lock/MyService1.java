package com.dxc.lock;/**
 * Created by Administrator on 2018-06-01.
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-8:52
 */
public class MyService1{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean hasValue = false;
    public void set()  {
       try{
           lock.lock();
           while (hasValue == true){
               condition.await(); //如果有值就暂停
           }
           System.out.println("$$$$$$$$$");
           hasValue = true;
           condition.signal(); // 通知
       }catch (Exception e){
           lock.unlock();
       }
    }

    public void get() {
        try{
            lock.lock();
            while (hasValue == false){
                condition.await();
            }
            System.out.println("^^^^^^^^^");
            hasValue = false;
            condition.signal();
        }catch (Exception e){
            lock.unlock();
        }
    }

}
