package com.dxc.lock.rw;/**
 * Created by Administrator on 2018-06-01.
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-10:33
 */
public class RWService {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public void write(){
        try{
            writeLock.lock();
            System.out.println(Thread.currentThread().getId() + "获得写锁");
            TimeUnit.SECONDS.sleep(5);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    public void read(){
        try{
            readLock.lock();
            System.out.println(Thread.currentThread().getId() + "获得读锁");
            TimeUnit.SECONDS.sleep(20);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readLock.unlock();
        }
    }

}
