package xzy.designPattern.proxy;

import org.junit.Test;
import xzy.designPattern.proxy.cglibProxy.SubjectCGlibMethodInterceptor;
import xzy.designPattern.proxy.jdkProxy.SubjectInvocation;
import xzy.designPattern.proxy.jdkProxyFactory.InvocationFactory;
import xzy.designPattern.proxy.jdkProxyFactory.ProxyFactory;

public class ProxyTest {
    @Test
    public void test0() {
        SubjectInterface subject = new SubjectImpl();
        SubjectInvocation subjectInvocation = new SubjectInvocation();
        subjectInvocation.setObject(subject);
        SubjectInterface proxyObject = (SubjectInterface) subjectInvocation.getProxyInstance();
        proxyObject.doSomething("1", 1);
    }

    @Test
    public void test1() {
        SubjectInterface subjectImpl = new SubjectImpl();
        InvocationFactory<SubjectInterface> invocationFactory = new InvocationFactory<>(subjectImpl);
        ProxyFactory<SubjectInterface> proxyFactory = new ProxyFactory<>(invocationFactory, SubjectInterface.class);
        SubjectInterface proxyObject = proxyFactory.newInstance();
        proxyObject.doSomething("1", 1);
    }

    @Test
    public void test2() {
        SubjectCGlibMethodInterceptor subjectCGlibMethodInterceptor = new SubjectCGlibMethodInterceptor();
        SubjectImpl subject = (SubjectImpl) subjectCGlibMethodInterceptor.getInstance(new SubjectImpl());
        subject.doSomething("1", 1);
    }

}
