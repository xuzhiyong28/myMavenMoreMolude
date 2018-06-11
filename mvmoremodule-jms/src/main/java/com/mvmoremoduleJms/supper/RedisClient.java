package com.mvmoremoduleJms.supper;/**
 * Created by Administrator on 2018-04-09.
 */

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author xuzhiyong
 * @createDate 2018-04-09-10:04
 */
public class RedisClient {
    public static JedisPool jedisPool = null;

    public static void initJedisPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(20);
        config.setMaxWaitMillis(1000l);
        RedisClient.jedisPool = new JedisPool(config,"localhost");
    }



}
