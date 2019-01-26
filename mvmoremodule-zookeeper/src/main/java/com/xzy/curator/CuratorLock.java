package com.xzy.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/****
 * Curator提供了InterProcessMutex类来帮助我们实现分布式锁，其内部就是使用的EPHEMERAL_SEQUENTIAL类型节点
 */
public class CuratorLock {

    private  static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("192.168.135.133:2181").retryPolicy(new ExponentialBackoffRetry(3000, 3)).build();
    public static void main(String[] args){
        client.start();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 5 ; i++){
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        dowork();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedThreadPool.shutdown();
    }

    public static void dowork() throws Exception {
        InterProcessMutex ipm = new InterProcessMutex(client,"/distributed_lock");
        try{
            ipm.acquire();
            System.out.println("Thread ID:" + Thread.currentThread().getId() + " acquire the lock");
            Thread.sleep(10000);
            System.out.println("Thread ID:" + Thread.currentThread().getId() + " release the lock");
        }catch (Exception e){

        }finally {
            ipm.release();
        }
    }
}
