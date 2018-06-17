package com.dxc.blockingQueue;/**
 * Created by Administrator on 2018-06-11.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-06-11-16:26
 */
public class ArrayBlockingQueueTest {


    public static void main(String args[]) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1);
        Thread threadA = new Thread(new Customer(blockingQueue));
        Thread threadB = new Thread(new Producer(blockingQueue));
        threadA.start();
        threadB.start();
    }

    /***
     * 消费者
     */
    static class Customer implements Runnable {
        private BlockingQueue<String> blockingQueue;

        public Customer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(Thread.currentThread().getName() + "消费者准备消费集合元素");
                try {
                    TimeUnit.SECONDS.sleep(1);
                    blockingQueue.take(); //取出元素
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "消费完成" + blockingQueue);
            }
        }
    }


    /***
     * 消费者
     */
    static class Producer implements Runnable {
        String[] str = new String[]{"solr", "lucene", "nutch"};
        private BlockingQueue<String> blockingQueue;

        public Producer(BlockingQueue<String> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 99999999; i++) {
                System.out.println(Thread.currentThread().getName() + "生产者准备生产集合元素了!");
                try {
                    Thread.sleep(2000);
                    //尝试放入元素，如果对列已满，则线程被阻塞
                    blockingQueue.put(str[i % 3]);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "生产完成:" + blockingQueue);
            }
        }
    }


}
