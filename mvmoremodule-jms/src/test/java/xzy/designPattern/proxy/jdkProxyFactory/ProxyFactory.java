package xzy.designPattern.proxy.jdkProxyFactory;

import java.lang.reflect.Proxy;

public class ProxyFactory<T> {

    private Class<T> subjectInterface;

    private InvocationFactory<T> invocationFactory;

    public ProxyFactory(InvocationFactory<T> invocationFactory, Class<T> subjectInterface) {
        this.invocationFactory = invocationFactory;
        this.subjectInterface = subjectInterface;
    }

    public T newInstance() {
        return (T) Proxy.newProxyInstance(subjectInterface.getClassLoader(), new Class[]{subjectInterface}, invocationFactory);
    }
}
