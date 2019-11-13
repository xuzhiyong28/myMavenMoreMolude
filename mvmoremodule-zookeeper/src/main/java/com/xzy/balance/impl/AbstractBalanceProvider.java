package com.xzy.balance.impl;

import com.xzy.balance.client.BalanceProvider;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2019-11-13-22:50
 */
public abstract class AbstractBalanceProvider<T> implements BalanceProvider<T> {

    //具体的负载均衡算法有子类提供
    protected abstract T balanceAlgorithm(List<T> items);

    protected abstract List<T> getBalanceItems();


    @Override
    public T getBalanceItem() {
        return balanceAlgorithm(getBalanceItems());
    }
}
