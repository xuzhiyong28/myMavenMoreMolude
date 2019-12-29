package com.dxc.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2019-12-29-21:55
 * 1. 有界(初始化传入一个长度，如果没有就是Integer.MAX,也相当于无界了) + 单链表(只能指向下一个元素) + 阻塞
 * 2. 没有扩容的说法
 * 3. 底层采用 两个ReentrantLock[takeLock,putLock] + 两个Condition[notEmpty,notFull]来实现阻塞和线程安全
 * 4. 用两把锁实现入队出队相互不阻塞，因为是FIFO，所以入队都是从尾节点开始，出队都是从从节点开始。相互不影响。
 *
 */
public class LinkedBlockingQueueTest {

    public static void main(String args[]) {
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(1);
        Thread threadA = new Thread(new ArrayBlockingQueueTest.Customer(blockingQueue));
        Thread threadB = new Thread(new ArrayBlockingQueueTest.Producer(blockingQueue));
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
