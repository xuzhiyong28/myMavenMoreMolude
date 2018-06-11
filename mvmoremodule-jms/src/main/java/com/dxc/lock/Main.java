package com.dxc.lock;/**
 * Created by Administrator on 2018-06-01.
 */

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-8:45
 */
public class Main {

    public static void main(String agrs[]){
        MyService myService = new MyService();
        MyThread[] myThreads = new MyThread[10];
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i < myThreads.length ; i++){
            myThreads[i] = new MyThread(myService);
            executorService.execute(myThreads[i]);
        }
    }

    static class MyThread extends Thread{
        private MyService myService;
        public MyThread(MyService myService){
            this.myService = myService;
        }
        @Override
        public void run() {
            myService.testMethod1();
        }
    }
}
