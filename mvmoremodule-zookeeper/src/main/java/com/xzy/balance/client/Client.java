package com.xzy.balance.client;

public interface Client {
    // 连接服务器
    public void connect() throws Exception;
    // 断开服务器
    public void disConnect() throws Exception;
}
