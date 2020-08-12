package xzy.dxc;

import com.dxc.atomic.CASQueue;
import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {

    @Test
    public void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        int andSet = atomicInteger.getAndSet(2);
        System.out.println(andSet);
        System.out.println(atomicInteger.get());
    }

    /***
     * 无锁队列测试 -- 入队
     */
    @Test
    public void test2() throws InterruptedException {
        CASQueue<String> casQueue = new CASQueue<>();
        int poolCount = 50;
        ExecutorService executorService = Executors.newFixedThreadPool(poolCount);
        CountDownLatch countDownLatch = new CountDownLatch(poolCount);
        for (int i = 0; i < poolCount; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0 ; j < 1000; j++){
                        casQueue.enQueue(UUID.randomUUID().toString());
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(casQueue.getQueueSize());
    }
}
