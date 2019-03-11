package com.rateLimiter;

/****
 * 单机-- 漏桶限流量
 * https://www.jianshu.com/p/d9504fc0af4d
 * 进水的速率是不确定的，但是出水的速率是恒定的，当水满的时候是会溢出的,桶的大小恒定
 */
public class RateLimiterBucketDemo {
    //时间刻度
    private static long time = System.currentTimeMillis();
    //桶里面现在的水
    private static int water = 0;
    //桶的大小
    private static int size = 100;
    //出水速率
    private static int rate = 3;
    public static boolean grant(){
        long now = System.currentTimeMillis();
        int out = (int)((now - time) / 700 * rate);
        //漏水后的剩余
        water = Math.max(0 , water - out);
        time = now;
        if((water + 1) < size){
            ++water;
            return true;
        }else{
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
