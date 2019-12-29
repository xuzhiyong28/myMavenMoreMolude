package com.dxc.blockingQueue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuzhiyong
 * @createDate 2019-12-29-16:04
 * 使用ConcurrentLinkedQueue 注意点
 * 队列中 size() 和  isEmpty() 方法的使用, 对于大容量的队列，慎用size()方法,因为其内部实现会对整个对列的
 * 元素进行循环遍历，非常耗时
 * 判空处理时, 使用isEmpty()方法, 切记不要使用  size() == 0  这种写法
 */
public class ConcurrentLinkedQueueTest {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
    private static int count = 100000;
    private static final int THEAD_COUNT = 2;
    private static CountDownLatch countDownLatch = new CountDownLatch(THEAD_COUNT);

    static {
        for (int i = 0; i < 100000; i++) {
            queue.offer(i);
        }
    }

    public static void main(String agrs[]) throws InterruptedException {
        long timeStart = System.currentTimeMillis();
        ExecutorService executorService = Executors.newFixedThreadPool(THEAD_COUNT);
        for (int i = 0; i < THEAD_COUNT; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    while (!queue.isEmpty()) {
                        System.out.println(queue.poll());
                    }
                    //不要使用size() == 0 来判断是否为空，这个耗时很长
                    /*while (queue.size() > 0){
                        System.out.println(queue.poll());
                    }*/
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("耗时 :" + (System.currentTimeMillis() - timeStart));
        executorService.shutdown();
    }
}
