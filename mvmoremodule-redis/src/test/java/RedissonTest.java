import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonTest {
    private static Config config = new Config();
    private static Redisson redisson = null;
    private static final String LOCK_TITLE = "redisLock_";

    static {
        config.useSingleServer().setAddress("127.0.0.1:6379");
        redisson = (Redisson) Redisson.create(config);
    }


    @Test
    public void acquireTest() {
        acquire("xuzyLock");
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
            return false;
        }
        return true;
    }

    /***
     * 解锁
     * @param lockName
     */
    public static void release(String lockName){
        String key = LOCK_TITLE + lockName;
        //获取锁对象
        RLock mylock = redisson.getLock(key);
        mylock.unlock();
    }

}
