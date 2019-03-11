package com.rateLimiter;

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
    private static long reqCount = 0;

    public static boolean grant(){
        long now = System.currentTimeMillis();
        if(now < timeStamp + limitCount){
            if(reqCount < limitCount){
                ++reqCount;
                return true;
            }else{
                return false;
            }
        }else{
            timeStamp = System.currentTimeMillis();
            reqCount = 0;
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
