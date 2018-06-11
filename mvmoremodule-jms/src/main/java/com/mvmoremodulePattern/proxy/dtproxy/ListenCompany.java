package com.mvmoremodulePattern.proxy.dtproxy;/**
 * Created by Administrator on 2018-05-13.
 */

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuzhiyong
 * @createDate 2018-05-13-13:42
 * InvocationHandler 和 Proxy类配合使用
 */
public class ListenCompany implements InvocationHandler {



    private Object object;


    public void setObject(Object object) {
        this.object = object;
    }

    public ListenCompany(){
    }


    public Object newProxyInstance(){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        beforeSale();
        Object result = method.invoke(object,args);
        afterSale();
        return result;
    }

    public void beforeSale(){
        System.out.println("===提供包装服务===");
    }

    public void afterSale(){
        System.out.println("===提供包退服务===");
    }
}
