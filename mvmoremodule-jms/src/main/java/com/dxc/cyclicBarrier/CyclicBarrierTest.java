package com.dxc.cyclicBarrier;/**
 * Created by Administrator on 2018-06-01.
 */

import com.google.common.collect.Maps;
import edu.emory.mathcs.backport.java.util.concurrent.Executor;

import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-17:25
 * 调用三次await后放开栅栏
 */
public class CyclicBarrierTest {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }
    public static void test() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3); //定义三个等待
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Thread(new Runner(cyclicBarrier, "1.")));
        executorService.execute(new Thread(new Runner(cyclicBarrier, "2.")));
        executorService.execute(new Thread(new Runner(cyclicBarrier, "3.")));
        executorService.shutdown();
    }




    static class Runner implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private String name;

        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000 * (new Random()).nextInt(8));
                System.out.println(name + " 准备好了...");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("=====起跑=====");
        }
    }



    public static void test2() throws InterruptedException {
        int NUMBER = 3000;
        Map<String, String> concurrentMap = Maps.newConcurrentMap();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER);
        for(int i = 0 ; i < NUMBER ; i++){
            executorService.execute(new Thread(new Runner2(cyclicBarrier,concurrentMap)));
        }
        executorService.awaitTermination(60 * 2, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(concurrentMap.keySet().size());
    }



    static class Runner2 implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private Map<String, String> concurrentMap;

        public Runner2(CyclicBarrier cyclicBarrier, Map<String, String> concurrentMap) {
            this.cyclicBarrier = cyclicBarrier;
            this.concurrentMap = concurrentMap;
        }

        @Override
        public void run() {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("====全部开始执行====");
            for (int i = 0; i < 1000; i++) {
                String uuid = UUID.randomUUID().toString();
                //uuid = uuid.substring(0, uuid.indexOf("-"));
                concurrentMap.put(uuid, "1");
            }
        }
    }
}
