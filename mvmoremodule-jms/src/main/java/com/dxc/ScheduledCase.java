package com.dxc;/**
 * Created by Administrator on 2018-05-19.
 */

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-05-19-18:09
 */
public class ScheduledCase {
    public static void main(String agrs[]){
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        /*scheduledThreadPoolExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            }
        },3000, TimeUnit.MICROSECONDS);*/

        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis());
            }
        },1000,3000,TimeUnit.MICROSECONDS);
    }
}
