package com.xzy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect // 定义切面
public class AnnotationMyAdvice {

    @Pointcut("execution(* com.xzy.aop.service..*.*(..))")
    public void pointcut001(){

    }

    @Before("pointcut001()")
    public void before() {
        System.out.println("前置通知");
    }

    //后置通知
    @AfterReturning("pointcut001()")
    public void afterReturn() {
        System.out.println("后置通知 (只出现在没有发生异常)");
    }

    //环绕通知 常用
    //需要ProceedingJoinPoint接口作为参数
    @Around("pointcut001()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("环绕通知的之前部分");
        Object proceed = pjp.proceed();
        System.out.println("环绕通知的之后部分");
        return proceed;
    }

    //异常抛出通知
    @AfterThrowing(pointcut = "pointcut001()", throwing = "ex")
    public void afterException() {
        System.out.println("异常出现之后的通知");
    }

    //最终通知
    @After("pointcut001()")
    public void after() {
        System.out.println("后置通知(不管是否发生异常)");
    }

}
