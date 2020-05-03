package com.dxc.executor;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.RandomUtils;

import java.lang.annotation.Target;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolTest {

    private static final ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();


    public void test0(){
        /***
         * schedule 定时任务只有执行一次
         */
        scheduledThreadPool.schedule(() -> System.out.println("===任务执行一次"),1,TimeUnit.SECONDS);
    }


    /***
     * scheduleAtFixedRate的间隔时间从上个任务的开始时间起算
     */
    public void test1(){
        /***
         * scheduleAtFixedRate -- 每间隔若干时间周期启动定时任务
         * initialDelay -- 首次执行的延长时长
         * period -- 后续运行的间隔时长
         * 第四个参数 -- 时长单位
         */
        scheduledThreadPool.scheduleAtFixedRate(
                () -> {
                    System.out.println("执行时间:" + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    try {
                        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1,10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ,0,3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(
                () -> {
                    System.out.println("任务开始执行===" + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    try {
                        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1,10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("任务结束执行===" + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                }
                ,0,3, TimeUnit.SECONDS);
    }

    /***
     * 间隔时间从上个任务的结束时间起算
     */
    public void test2(){
        scheduledThreadPool.scheduleWithFixedDelay(
                () -> {
                    System.out.println("任务开始执行===" + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    try {
                        TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1,10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("任务结束执行===" + DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
                }
                ,0,3, TimeUnit.SECONDS);

    }


    public static void main(String[] args){
        NewScheduledThreadPoolTest newScheduledThreadPoolTest = new NewScheduledThreadPoolTest();
        newScheduledThreadPoolTest.test2();
    }

}
