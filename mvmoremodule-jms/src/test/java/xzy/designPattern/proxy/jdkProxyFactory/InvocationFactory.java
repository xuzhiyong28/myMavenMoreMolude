package xzy.designPattern.proxy.jdkProxyFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationFactory<T> implements InvocationHandler {

    private T object;

    public InvocationFactory(T object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=========Before-JDK动态代理对业务进行了增强处理=========");
        Object invoke = method.invoke(object, args);
        System.out.println("=========after-JDK动态代理对业务进行了增强处理=========");
        return invoke;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }
}
