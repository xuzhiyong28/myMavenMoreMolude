package com.dxc.blockingQueue;/**
 * Created by Administrator on 2018-06-11.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-06-11-16:26
 * 特点:
 *  1.有界 + 数组 + 阻塞
 *  2.不需要扩容，初始化时要指定数组的长度，当满时返回false或者报错
 *  3.底层利用两个变量 takeIndex 和 putIndex 循环利用数组
 *   3.1 putIndex用来判断当前数组入队的指针，当到达最后一个时就重新设置成0，从头开始循环
 *   3.2 takeIndex用来判断当前数组出队的指针，当到达最后一个时就重新设置成0，从头开始循环
 *  4. 底层采用ReentrantLock + notEmpty(数组不为空Condition) + notFull(数组为空Condition) 来控制。
 *     所以效率低，出队和入队都用同一个锁，一次性只能有一个入队一个出队 并且会使用Thread.yield()使得其他线程让出CPU
 *  5.为什么PriorityBlockingQueue不需要notFull条件
 *     因为没有满的情况，满了会自动扩容，所以不会满
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
