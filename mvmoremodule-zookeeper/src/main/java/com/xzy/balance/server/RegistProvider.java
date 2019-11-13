package com.xzy.balance.server;

/***
 * 注册提供者
 * 用来将服务器注册到zk或者从zk上注销
 */
public interface RegistProvider {
    public void regist(Object context) throws Exception;
    public void unRegist(Object context) throws Exception;
}
