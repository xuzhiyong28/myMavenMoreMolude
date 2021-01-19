package com.mvmoremodulePattern.proxy.factoryproxy;

import java.lang.reflect.Proxy;

/***
 * 代理工厂
 * @param <T>
 */
public class ProxyFactory<T> {
    //需要代理的接口
    private final Class<T> mapperInterface;

    public ProxyFactory(Class<T> mapperInterface){
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(ProxyInvocationHandler<T> mapperProxy){
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }

    public Class<T> getMapperInterface() {
        return mapperInterface;
    }
}
