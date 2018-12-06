package xzy.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {
    RateLimiter rateLimiter;

    @Before
    public void before(){
        //一秒钟生成5个令牌
        rateLimiter = RateLimiter.create(10.0);
    }

    @Test
    public void test1(){
        long startTime = System.currentTimeMillis();
        for(int i = 0 ; i < 100; i++){
            rateLimiter.acquire();
            System.out.println("call execute.." + i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
    }

    @Test
    public void test2(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        final RateLimiter rateLimiter2 = RateLimiter.create(5.0);
        for(int i = 0 ; i < 10 ; i++){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if(rateLimiter2.tryAcquire(1000,TimeUnit.MICROSECONDS)){
                        System.out.println("aceess success [" + sdf.format(new Date()) + "]");
                    }else{
                        System.out.println("aceess limit [" + sdf.format(new Date()) + "]");
                    }
                }
            });
        }
        try {
            executorService.awaitTermination(10,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
