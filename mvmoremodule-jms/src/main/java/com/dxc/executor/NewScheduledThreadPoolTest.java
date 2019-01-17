package com.dxc.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NewScheduledThreadPoolTest {

    private static final ScheduledExecutorService scheduledThreadPool = Executors.newSingleThreadScheduledExecutor();

    public void test1(){
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("!!!!");
            }
        },0,100, TimeUnit.MICROSECONDS);
    }

    public static void main(String[] args){
        NewScheduledThreadPoolTest newScheduledThreadPoolTest = new NewScheduledThreadPoolTest();
        newScheduledThreadPoolTest.test1();
    }

}
