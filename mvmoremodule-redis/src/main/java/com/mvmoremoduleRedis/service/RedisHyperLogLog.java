package com.mvmoremoduleRedis.service;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

/***
 * redis的pfadd可以用来统计不重复的数据，例如一个用户一天之内的多次访问请求只能计数一次
 * pfadd www.xuzy.com/119989.html_20180101 userid
 * 表示20180101 userid访问119989.html页面，如果这个ID又来访问的话就不会记录进去
 * pfcount www.xuzy.com/119989.html_20180101 获取119989.html页面20180101被访问的次数
 */
@Component("redisHyperLogLog")
public class RedisHyperLogLog {
    @Autowired
    private JedisConnectionFactory factory;
    private static final String KEY = "hyper";

    public void init() {
        Jedis jedis = factory.getConnection().getNativeConnection();
        for (int i = 0; i < 10000; i++) {
            jedis.pfadd(KEY, String.valueOf(RandomUtils.nextInt(0, 100)));
            long total = jedis.pfcount(KEY);
            System.out.println("total=" + total);
        }
    }

}
