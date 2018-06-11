package com.mvmoremodulePattern.SingletonPattern;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-9:22
 * 懒汉式单例模式 - 线程安全
 * 是否 Lazy 初始化：是
   是否多线程安全：是
   优点：第一次调用才初始化，避免内存浪费。
   缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 */
public class SingletonTwo {
    private static SingletonTwo singletonTwo;
    private SingletonTwo(){};
    private synchronized static SingletonTwo getInstance(){
        if(singletonTwo == null)
            singletonTwo = new SingletonTwo();
        return singletonTwo;
    }
}
