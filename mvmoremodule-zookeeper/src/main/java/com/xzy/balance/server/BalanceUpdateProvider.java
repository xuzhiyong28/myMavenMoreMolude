package com.xzy.balance.server;

/***
 * 用来修改服务器负载信息
 */
public interface BalanceUpdateProvider {
    // 增加负载
    public boolean addBalance(Integer step);

    // 减少负载
    public boolean reduceBalance(Integer step);
}
