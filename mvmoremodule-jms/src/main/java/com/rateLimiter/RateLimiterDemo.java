package com.rateLimiter;/**
 * Created by Administrator on 2019-01-21.
 */

import com.google.common.util.concurrent.RateLimiter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-01-21-22:16
 */
public class RateLimiterDemo {
    public static void main(String[] args){
        RateLimiterDemo rateLimiterDemo = new RateLimiterDemo();
        rateLimiterDemo.test1();
    }

    public void test1(){
        //新建一个每秒限制3个的令牌桶
        final RateLimiter rateLimiter = RateLimiter.create(3.0);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for(int i = 0 ; i < 10 ; i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //不限流的话,一下子就结束了
                    //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    if (rateLimiter.tryAcquire(1, 10, TimeUnit.SECONDS)) {
                        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }
                }
            });
        }
        executor.shutdown();
    }


    public void test2(){
        final RateLimiter rateLimiter = RateLimiter.create(3.0);
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10 ; i++){
            final  int no = i;
            executor.submit(new Runnable() {
                @Override
                public void run() {
                   rateLimiter.acquire();
                    System.out.println("Accessing: " + no + ",time:"
                            + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
                }
            });
        }
        executor.shutdown();
    }

}
