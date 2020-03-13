import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
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
        config.setLockWatchdogTimeout(10 * 1000);
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
        String key = LOCK_TITLE + "_lock";
        RLock mylock = redisson.getLock(key);
        mylock.lock();
        mylock.lock();
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
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rLock.unlock();
            }
        }
    }


}
