package com.dxc.blockingQueue;/**
 * Created by Administrator on 2018-06-11.
 */

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuzhiyong
 * @createDate 2018-06-11-16:26
 */
public class ArrayBlockingQueueTest {


    public static void main(String args[]){
        ReentrantLock lock = new ReentrantLock();
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(1); //创建一个1容量的队列
        ExecutorService executorService = Executors.newFixedThreadPool(10); //创建20个线程来处理
        executorService.execute(new Customer(queue));
        executorService.execute(new Customer(queue));
        executorService.execute(new Customer(queue));
        executorService.execute(new Customer(queue));
        executorService.execute(new Customer(queue));
        executorService.execute(new Producer(queue));
        executorService.execute(new Producer(queue));
        executorService.execute(new Producer(queue));
    }


    static  class Customer implements Runnable{
        private ArrayBlockingQueue<String> queue;
        private ReentrantLock lock;
        public Customer(ArrayBlockingQueue<String> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            while(true){
                try {
                    System.out.println(Thread.currentThread() + "_消费者消费：" + queue.take());
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        }
    }

    /***
     * 生产者
     */
    static class Producer implements Runnable{
        private ArrayBlockingQueue<String> queue;
        public Producer(ArrayBlockingQueue<String> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            while (true){
                int num = (int) (Math.random() * 3);
                try {
                    queue.put(String.valueOf(num)); //put 放入队列，阻塞方法
                    System.out.println("生产产品编号为:" + num);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                }
            }
        }
    }

}
