package com.dxc;/**
 * Created by Administrator on 2018-05-19.
 */

import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2018-05-19-15:44
 */
public class UseThreadPool {


    public static void main(String agrs[]) {
        ExecutorService executorService = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardOldestPolicy());
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        //ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i <= 6; i++) {
            Worker worker = new Worker("worker_" + i);
            System.out.println("A new task has been added : " + worker.getTaskName());
            executorService.execute(worker);
        }
        executorService.shutdown();
    }


    public static class Worker implements Runnable {

        private String taskName;
        private Random random = new Random();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "process the taks :" + taskName);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
