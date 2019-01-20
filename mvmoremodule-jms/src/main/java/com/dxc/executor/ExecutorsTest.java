package com.dxc.executor;/**
 * Created by Administrator on 2019-01-16.
 */

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-01-16-20:43
 */
public class ExecutorsTest {

    public static void main(String[] agrs){
        //ExecutorsTest.newFixedThreadPoolTest();
        //ExecutorsTest.newSingleThreadExecutorTest();
        //ExecutorsTest.newCachedThreadPoolTest();
    }


    public static void newCachedThreadPoolTest(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10 ; i++){
            final int index = i;
            try{
                TimeUnit.SECONDS.sleep(i);
            }catch (Exception e){
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }


    public static void newSingleThreadExecutorTest(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0 ;i <=10 ; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }

    public static void newFixedThreadPoolTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i = 0 ;i <=10 ; i++){
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }

}
