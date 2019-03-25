package com.mvmoremoduleRedis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RedisOtherDemo {
    @Autowired
    private RedisTemplate redisTemplate;

    public List<String> getValueBySql(String key){
        System.out.println("这里模拟从数据库中获取数据");
        return new ArrayList<>();
    }
    public List<String> getCache(String key){
        List<String> resultList = (List<String>)redisTemplate.opsForValue().get(key);
        if(resultList == null || CollectionUtils.isEmpty(resultList)){
            resultList = getValueBySql(key);
            redisTemplate.opsForValue().set(key, resultList, 1000, TimeUnit.SECONDS);
            return resultList;
        }else{
            return resultList;
        }
    }

    /***
     * synchronized + 双重检查机制
     * @param key
     * @return
     */
    public List<String> getCacheSave(String key){
        List<String> resultList = (List<String>)redisTemplate.opsForValue().get(key);
        if(resultList == null || CollectionUtils.isEmpty(resultList)){
            synchronized (this){
                resultList = (List<String>)redisTemplate.opsForValue().get(key);
                if(CollectionUtils.isEmpty(resultList)){
                    return resultList;
                }
                resultList = getValueBySql(key);
                redisTemplate.opsForValue().set(key, resultList, 1000, TimeUnit.SECONDS);
                return resultList;
            }
        }else{
            return resultList;
        }
    }

    public List<String> getCacheSave2(String key) throws InterruptedException {
        List<String> resultList = (List<String>)redisTemplate.opsForValue().get(key);
        if(CollectionUtils.isEmpty(resultList)){
            final String mutexKey = key + "_lock";
            boolean isLock = (Boolean) redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    //只在键 key 不存在的情况下， 将键 key 的值设置为 value,若键 key 已经存在， 则 SETNX 命令不做任何动作
                    //命令在设置成功时返回 1 ， 设置失败时返回 0
                    return connection.setNX(mutexKey.getBytes(),"1".getBytes());
                }
            });
            if(isLock){

            }
        }else{
            //线程休息50毫秒后重试
            Thread.sleep(50);
            return getCacheSave2(key);
        }
        return resultList;
    }

}
