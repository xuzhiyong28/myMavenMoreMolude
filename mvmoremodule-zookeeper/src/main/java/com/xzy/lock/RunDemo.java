package com.xzy.lock;/**
 * Created by Administrator on 2018-07-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-07-26-21:37
 */
public class RunDemo implements  Runnable {
    @Override
    public void run() {
        DistributedLock lock = null;
        try{
            lock = new DistributedLock("127.0.0.1:2181", "test1");
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "正在运行");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(lock != null)
                lock.unlock();
        }
    }
}
