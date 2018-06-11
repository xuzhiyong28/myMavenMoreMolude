package com.mvmoremoduleJms.supper;/**
 * Created by Administrator on 2018-04-09.
 */

import redis.clients.jedis.Jedis;

/**
 * @author xuzhiyong
 * @createDate 2018-04-09-10:58
 */
public class SubThread extends  Thread {
    private final RedisSubscriber subscriber = new RedisSubscriber();
    private final String channel = "mychat";
    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try{
            jedis = RedisClient.jedisPool.getResource();
            jedis.subscribe(subscriber,channel);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
