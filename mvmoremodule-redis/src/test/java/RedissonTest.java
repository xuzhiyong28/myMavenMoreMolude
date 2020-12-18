import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.springTest.BeanA;
import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.redisson.config.Config;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedissonTest {
    private static Config config = new Config();
    private static Redisson redisson = null;
    private static final String LOCK_TITLE = "redisLock_";

    static {
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        config.setLockWatchdogTimeout(10 * 1000); //锁超时时间
        redisson = (Redisson) Redisson.create(config);
    }


    @Test
    public void test0(){
    }


    @Test
    public void test1() {
        RAtomicLong longObject = redisson.getAtomicLong("myLong");
        boolean b = longObject.compareAndSet(0, 400);
        Assert.assertEquals(b, true);
        longObject.getAndIncrement();
        Assert.assertEquals(longObject.get(), 401);
        longObject.delete();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        RAtomicLong longObject = redisson.getAtomicLong("myLong");
        RFuture<Boolean> booleanRFuture = longObject.compareAndSetAsync(0, 400);
        booleanRFuture.whenCompleteAsync((aBoolean, throwable) -> {
            System.out.println("===whenCompleteAsync is complete===");
        }, Executors.newCachedThreadPool());
        booleanRFuture.get();
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void test3(){
        RBucket<BeanA> bucket = redisson.getBucket("myObject");
        bucket.set(new BeanA("beanA"),10,TimeUnit.SECONDS);
        BeanA beanA = bucket.get();
        System.out.println(beanA);
    }





    @Test
    public void testCountDown() throws InterruptedException {
        RCountDownLatch latch = redisson.getCountDownLatch("anyCountDownLatch");
        latch.trySetCount(10);
        for(int i = 0 ; i < 10 ; i++){
            Thread thread = new Thread(new Runner2(latch));
            thread.start();
        }
        latch.await();
        System.out.println("======子线程都已经执行结束=======");
    }


    /***
     * 看门狗
     */
    @Test
    public void testLockWatchDog(){
        String key = LOCK_TITLE + "_lock";
        RLock mylock = redisson.getLock(key);
        try{
            if(mylock.tryLock()){
                System.out.println("加锁成功");
            }
        }finally {
            mylock.unlock();
        }
    }


    @Test
    public void testLock() throws InterruptedException {
        String key = LOCK_TITLE + "_lock";
        RLock mylock = redisson.getLock(key);
        Map<String, String> myMap = Maps.newHashMap();
        List<Thread> threadList = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runner(mylock, myMap));
            thread.start();
            threadList.add(thread);
        }
        for (Thread thread : threadList) {
            thread.join();
        }
        System.out.println(myMap.entrySet().size());
    }


    @Test
    public void testTopic(){
        RTopic<String> topic = redisson.getTopic("anyTopic");
        topic.addListener((channel, str) -> {
            System.out.println(channel);
            System.out.println(str);
        });
    }

    @Test
    public void testTopicPush(){
        RTopic<String> topic = redisson.getTopic("anyTopic");
        long clientsReceivedMessage = topic.publish("My Name is xuzhiyong");
    }




    class Runner implements Runnable {
        private RLock rLock;
        private Map<String, String> myMap;
        public Runner(RLock rLock, Map<String, String> myMap) {
            this.myMap = myMap;
            this.rLock = rLock;
        }

        @Override
        public void run() {
            rLock.lock();
            try {
                for (int i = 0; i < 200; i++) {
                    myMap.put(UUID.randomUUID().toString(), "1");
                }
                TimeUnit.SECONDS.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rLock.unlock();
            }
        }
    }


    class Runner2 implements  Runnable{
        private RCountDownLatch latch;

        public Runner2(RCountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("线程ID：" + Thread.currentThread().getName());
            latch.countDown();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
