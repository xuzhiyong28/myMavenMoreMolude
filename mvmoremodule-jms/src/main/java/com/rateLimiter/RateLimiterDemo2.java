package com.rateLimiter;/**
 * Created by Administrator on 2019-01-21.
 */

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentMap;

/**
 * @author xuzhiyong
 * @createDate 2019-01-21-22:31
 */
public class RateLimiterDemo2 {
    //key-value (service,Qps) ，接口服务的限制速率
    private static final ConcurrentMap<String, Double> resourceMap = Maps.newConcurrentMap();
    //userkey-service,limiter ,限制用户对接口的访问速率
    private static final ConcurrentMap<String, RateLimiter> userResourceLimiterMap = Maps.newConcurrentMap();

    static {
        resourceMap.put("methodA", 10.0);
        resourceMap.put("methodB", 5.0);
    }

    public static void updateResourceQps(String resource, double qps) {
        resourceMap.put(resource, qps);
    }

    public static void removeResource(String resource) {
        resourceMap.remove(resource);
    }

    public static int enter(String resource, String userKey) {
        long t1 = System.currentTimeMillis();
        Double qps = resourceMap.get(resource);

        //空的话不限流
        if (qps == null || qps.doubleValue() == 0) {
            return 0;
        }

        String keySer = resource + userKey;
        RateLimiter rateLimiter = userResourceLimiterMap.get(keySer);
        if (rateLimiter == null) {
            rateLimiter = RateLimiter.create(qps);
            RateLimiter putByOtherThread = userResourceLimiterMap.putIfAbsent(keySer, rateLimiter);
            if (putByOtherThread != null) {
                rateLimiter = putByOtherThread;
            }
            rateLimiter.setRate(qps);
        }

        if (!rateLimiter.tryAcquire()) {
            //限速，提示用户
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; "
                    + resource + " visited too frequently by key:" + userKey);
            return 99;
        }else{
            System.out.println("use :" + (System.currentTimeMillis() - t1) + "ms ; " );
            return 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //RateLimiterDemo2.test1();
        RateLimiterDemo2.test2();
    }


    public static void test1() throws InterruptedException {
        int i = 0;
        while(true){
            i++;
            long t2 = System.currentTimeMillis();
            System.out.println(" begin:" + t2 + " , hanchao:" + i);
            int  result = RateLimiterDemo2.enter("methodA","hanchao");
            if (result == 99) {
                i = 0;
                Thread.sleep(1000);
            }
        }
    }

    public static void test2() throws InterruptedException {
        RateLimiterDemo2.updateResourceQps("methodB",5.0);
        int y = 0;
        while(true){
            y++;
            long t3 = System.currentTimeMillis();
            System.out.println(" begin:" + t3 + " , tom:" + y);
            int result2 = RateLimiterDemo2.enter("methodB","tom");
            if (result2 == 99) {
                y = 0;
                Thread.sleep(1000);
            }
        }
    }
}
