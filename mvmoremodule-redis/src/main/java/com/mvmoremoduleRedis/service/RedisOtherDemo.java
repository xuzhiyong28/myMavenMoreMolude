package com.mvmoremoduleRedis.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Component("redisOtherDemo")
public class RedisOtherDemo {
    @Autowired
    private RedisTemplate redisTemplate;


    public List<String> getValueBySql(String key){
        System.out.println("这里模拟从数据库中获取数据");
        return Lists.newArrayList("1","2","3","4");
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

    /***
     *
     * @param key
     * @param retryCount 重试次数
     * @return
     * @throws InterruptedException
     */
    public List<String> getCacheSave2(String key,int retryCount) throws InterruptedException {
        List<String> resultList = (List<String>)redisTemplate.opsForValue().get(key);
        if(CollectionUtils.isEmpty(resultList)){
            final String mutexKey = key + "_lock";
            boolean isLock = (Boolean) redisTemplate.execute(new RedisCallback() {
                @Override
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    //只在键key不存在的情况下，将键key的值设置为value,若键key已经存在，则 SETNX 命令不做任何动作
                    //命令在设置成功时返回 1 ， 设置失败时返回 0
                    return connection.setNX(mutexKey.getBytes(),"1".getBytes());
                }
            });
            if(isLock){
                //设置成1秒过期
                redisTemplate.expire(mutexKey, 1000, TimeUnit.MILLISECONDS);
                resultList = getValueBySql(key);
                redisTemplate.opsForValue().set(key, resultList, 1000, TimeUnit.SECONDS);
                redisTemplate.delete(mutexKey);
            }else{
                //线程休息50毫秒后重试
                Thread.sleep(50);
                retryCount--;
                System.out.println("=====进行重试，当前次数:" + retryCount);
                if(retryCount == 0){
                    System.out.println("====这里发邮件或者记录下获取不到数据的日志，并为key设置一个空置防止重复获取");
                    List<String> list = Lists.newArrayList("no find");
                    redisTemplate.opsForValue().set(key, list, 1000, TimeUnit.SECONDS);
                    return list;
                }
                return getCacheSave2(key,retryCount);
            }
        }
        return resultList;
    }

}
