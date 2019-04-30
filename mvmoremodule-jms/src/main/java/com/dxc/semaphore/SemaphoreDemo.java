package com.dxc.semaphore;/**
 * Created by Administrator on 2018-10-13.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-10-13-20:17
 * Semaphore可以控制某个共享资源可被同时访问的次数,即可以维护当前访问某一共享资源的线程个数,并提供了同步机制.例如控制某一个文件允许的并发访问的数量.
 * <p>
 * 　　例如网吧里有100台机器,那么最多只能提供100个人同时上网,当来了第101个客人的时候,就需要等着,一旦有一个人人下机,就可以立马得到了个空机位补上去.这个就是信号量的概念.
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(); //获取一个信号量
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入，当前已有" + (3 - semaphore.availablePermits()) + "个并发");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    semaphore.release(); //归还信号量
                }
            };
            executorService.submit(runnable);
        }
        executorService.shutdown();
        executorService.awaitTermination(10,TimeUnit.MINUTES);
    }
}
