package com.xzy.aop;

import org.aspectj.lang.ProceedingJoinPoint;

/***
 * 切面
 */
public class MyAllAdvice {
    public void before() {
        System.out.println("前置通知");
    }

    //后置通知
    public void afterReturn() {
        System.out.println("后置通知 (只出现在没有发生异常)");
    }

    //环绕通知 常用
    //需要ProceedingJoinPoint接口作为参数
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知的之前部分");
        Object proceed = pjp.proceed();
        System.out.println("环绕通知的之后部分");
        return proceed;
    }

    //异常抛出通知
    public void afterException() {
        System.out.println("异常出现之后的通知");
    }

    //最终通知
    public void after() {
        System.out.println("后置通知(不管是否发生异常)");
    }

}
