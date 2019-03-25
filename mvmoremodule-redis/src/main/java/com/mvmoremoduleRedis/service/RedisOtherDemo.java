package com.mvmoremoduleRedis.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
