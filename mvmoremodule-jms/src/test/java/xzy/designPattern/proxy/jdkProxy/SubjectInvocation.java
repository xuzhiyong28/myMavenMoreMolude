package xzy.designPattern.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SubjectInvocation implements InvocationHandler {

    private Object object;

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("=========Before-JDK动态代理对业务进行了增强处理=========");
        Object invoke = method.invoke(object, args);
        System.out.println("=========after-JDK动态代理对业务进行了增强处理=========");
        return invoke;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
