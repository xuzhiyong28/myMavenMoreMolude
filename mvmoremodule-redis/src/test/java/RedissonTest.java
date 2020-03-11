import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedissonTest {
    private static Config config = new Config();
    private static Redisson redisson = null;
    private static final String LOCK_TITLE = "redisLock_";

    static {
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        redisson = (Redisson) Redisson.create(config);
    }


    @Test
    public void acquireTest() {
        System.out.println(acquire("xuzyLock"));
        System.out.println(acquire("xuzyLock"));
    }


    @Test
    public void testLock() throws InterruptedException {
        String key = LOCK_TITLE + "_lock";
        RLock mylock = redisson.getLock(key);
        Map<String,String> myMap = Maps.newHashMap();
        List<Thread> threadList = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new Runner(mylock,myMap));
            thread.start();
            threadList.add(thread);
        }
        for(Thread thread : threadList){
            thread.join();
        }
        System.out.println(myMap.entrySet().size());
    }


    /***
     * 加锁
     * @param lockName
     * @return
     */
    public static boolean acquire(String lockName) {
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redisson.getLock(key);
        //加锁，并且设置锁过期时间，防止死锁的产生
        try {
            mylock.lock(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /***
     * 解锁
     * @param lockName
     */
    public static void release(String lockName) {
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redisson.getLock(key);
        mylock.unlock();
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
