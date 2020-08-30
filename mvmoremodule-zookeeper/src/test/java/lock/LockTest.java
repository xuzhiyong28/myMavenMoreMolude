package lock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LockTest {
    /****
     * 测试加锁
     */
    @Test
    public void onlyLockTest1() {
        ZkOnlyLock zkOnlyLock = new ZkOnlyLock("lock001");
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    zkOnlyLock.lock();
                    System.out.println("做事ing");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    zkOnlyLock.unlock();
                }
            });
        }
    }

    /***
     * 测试超时
     * @throws InterruptedException
     */
    @Test
    public void onlyLockTest2() throws InterruptedException {
        ZkOnlyLock zkOnlyLock = new ZkOnlyLock("lock001");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkOnlyLock.lock(10,TimeUnit.SECONDS);
                System.out.println("====do=====");
                zkOnlyLock.unlock();
            }
        });
        thread.start();
        zkOnlyLock.lock();
        TimeUnit.SECONDS.sleep(30);
        zkOnlyLock.unlock();
        thread.join();
    }

    @Test
    public void zkOnlyFairLock01() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i = 0 ; i < 100; i++){
            executorService.submit(() -> {
                ZkOnlyFairLock zkLock = new ZkOnlyFairLock("lock");
                try {
                    zkLock.lock();
                    System.out.println("做事ing");
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    zkLock.unlock();
                }
            });
        }
        executorService.awaitTermination(10,TimeUnit.MINUTES);
    }

    @Test
    public void curtorLock001() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i = 0 ; i < 10; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(2000,3));
                    InterProcessLock lock = new InterProcessSemaphoreMutex(cf, "/curator/lock");
                    try{
                        cf.start();
                        lock.acquire();
                        System.out.println("做事ing");
                        TimeUnit.SECONDS.sleep(5);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.awaitTermination(10,TimeUnit.MINUTES);
    }


    @Test
    public void curtorLock002() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for(int i = 0 ; i < 10; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(2000,3));
                    InterProcessLock lock = new InterProcessMutex(cf, "/curator/lock");
                    try{
                        cf.start();
                        lock.acquire();
                        lock.acquire();
                        System.out.println("做事ing");
                        TimeUnit.SECONDS.sleep(5);
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        try {
                            lock.release();
                            lock.release();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        executorService.awaitTermination(10,TimeUnit.MINUTES);
    }



}
