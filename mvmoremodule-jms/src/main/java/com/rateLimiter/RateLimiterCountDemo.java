package com.rateLimiter;

import java.util.concurrent.atomic.AtomicLong;

/***
 * 单机 -- 计数器方式限流量
 * https://www.jianshu.com/p/d9504fc0af4d
 */
public class RateLimiterCountDemo {
    private static long timeStamp = System.currentTimeMillis();
    //限制1秒内上限为100请求
    private static long limitCount = 100;
    private static long interval = 1000;
    //请求数
    private static AtomicLong reqCount = new AtomicLong(0);

    public static boolean grant(){
        long now = System.currentTimeMillis();
        if(now < timeStamp + limitCount){
            if(reqCount.get() < limitCount){
                reqCount.incrementAndGet();
                return true;
            }else{
                return false;
            }
        }else{
            timeStamp = System.currentTimeMillis();
            reqCount = new AtomicLong(0);
            return false;
        }
    }

    public static void main(String[] args){
        for(int i = 0 ; i < 500 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(grant()){
                        System.out.println("执行业务逻辑");
                    }else{
                        System.out.println("限流");
                    }
                }
            }).start();
        }
    }
}
