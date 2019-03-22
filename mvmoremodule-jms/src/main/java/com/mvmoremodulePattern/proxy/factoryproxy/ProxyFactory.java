package com.mvmoremodulePattern.proxy.factoryproxy;

/***
 * 代理工厂
 * @param <T>
 */
public class ProxyFactory<T> {
    private final Class<T> mapperInterface;



    public ProxyFactory(Class<T> mapperInterface){
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(Proxy<T> mapperProxy){
        return (T) java.lang.reflect.Proxy.newProxyInstance(mapperInterface.getClassLoader(),new Class[]{mapperInterface},mapperProxy);
    }


    public Class<T> getMapperInterface() {
        return mapperInterface;
    }
}
