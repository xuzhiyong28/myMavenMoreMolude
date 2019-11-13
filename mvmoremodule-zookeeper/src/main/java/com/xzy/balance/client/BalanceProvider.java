package com.xzy.balance.client;

/***
 * 负载均衡算法提供者
 * @param <T>
 */
public interface BalanceProvider<T> {
    public T  getBalanceItem();
}
