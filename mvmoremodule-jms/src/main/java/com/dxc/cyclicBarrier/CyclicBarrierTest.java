package com.dxc.cyclicBarrier;/**
 * Created by Administrator on 2018-06-01.
 */

import edu.emory.mathcs.backport.java.util.concurrent.Executor;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-17:25
 * 调用三次await后放开栅栏
 */
public class CyclicBarrierTest {
    public static void main(String[] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3); //定义三个等待
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Thread(new Runner(cyclicBarrier,"1.")));
        executorService.execute(new Thread(new Runner(cyclicBarrier,"2.")));
        executorService.execute(new Thread(new Runner(cyclicBarrier,"3.")));
        executorService.shutdown();
    }

    static class Runner implements  Runnable{
        private CyclicBarrier cyclicBarrier;
        private String name;
        public Runner(CyclicBarrier cyclicBarrier,String name){
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try{
                Thread.sleep(1000 * (new Random()).nextInt(8));
                System.out.println(name + " 准备好了...");
                cyclicBarrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("=====起跑=====");
        }
    }
}
