package com.mvmoremodule.shiro.config;/**
 * Created by Administrator on 2018-06-07.
 */

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuzhiyong
 * @createDate 2018-06-07-9:00
 * 自定义凭证认证器
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username); //从缓存获取是否有登录次数的计数
        if(retryCount == null){ //不存在缓存则将数据放入
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username,retryCount);
        }
        if(retryCount.incrementAndGet() > 5){
            throw new ExcessiveAttemptsException("密码输入次数过多");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){
            passwordRetryCache.remove(username); //如果认证成功就删除计数
        }
        return matches;
    }
}
