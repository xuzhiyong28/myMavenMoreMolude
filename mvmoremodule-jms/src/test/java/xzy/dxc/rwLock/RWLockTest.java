package xzy.dxc.rwLock;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockTest {
    private static ReentrantReadWriteLock RWLock = new ReentrantReadWriteLock();
    private static Lock rLock = RWLock.readLock();
    private static Lock wLock = RWLock.writeLock();
    private static Map<String, String> map = Maps.newHashMap();

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        new Thread(new Runnable() {
            @Override
            public void run() {
                refreshMap();
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(getMap());
    }


    /***
     * 刷新缓存
     */
    public static void refreshMap() {
        try {
            wLock.lock();
            System.out.println("缓存开始更新");
            for (int i = 0; i < 100; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(UUID.randomUUID().toString(), "");
            }
            System.out.println("缓存更新完成");
        } finally {
            wLock.unlock();
        }
    }

    public static Map<String,String> getMap(){
        try{
            rLock.lock();
            System.out.println("获取Map");
            return map;
        }finally {
            rLock.unlock();
        }
    }


}
