package com.mvmoremodulePattern.proxy.factoryproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy<T> implements InvocationHandler {
    private T object;

    public Proxy(T object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("===before===");
        Object result = method.invoke(object,args);
        System.out.println("===after===");
        return result;
    }
}
