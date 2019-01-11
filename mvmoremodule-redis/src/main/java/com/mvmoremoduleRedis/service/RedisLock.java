package com.mvmoremoduleRedis.service;/**
 * Created by Administrator on 2018-04-28.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCommands;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author xuzhiyong
 * @createDate 2018-04-28-17:53
 */
@Component("redisLock")
public class RedisLock implements Lock {

    @Autowired
    private JedisConnectionFactory factory;

    @Autowired
    private RedisTemplate redisTemplate;

    private static final String LOCK = "LOCK";
    private static final String SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    private ThreadLocal<String> local = new ThreadLocal<String>();

    /***
     * 递归调用 循环加锁  阻塞式加锁
     */
    @Override
    public void lock() {
        if (!tryLock()) {
            try {
                Thread.sleep(new Random().nextInt(10) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock();
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /***
     * 加锁一次
     * @return
     */
    @Override
    public boolean tryLock() {
        final String value = UUID.randomUUID().toString();
        local.set(value);
        Jedis jedis = factory.getConnection().getNativeConnection();
        String ret = jedis.set(LOCK, value, "NX", "PX", 3000); //此操作为原子性操作
       /* String ret = (String) redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                JedisCommands commands = (JedisCommands) redisConnection.getNativeConnection();
                return commands.set(LOCK, value, "NX", "PX", 3000);
            }
        });*/
        if (ret != null && "OK".equals(ret))
            return true;
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Jedis jedis = (Jedis) factory.getConnection().getNativeConnection();
        jedis.eval(SCRIPT, Arrays.asList(LOCK), Arrays.asList(local.get()));
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
