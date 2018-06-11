package com.mvmoremodulePattern.SingletonPattern;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-9:18
 * 懒汉式单例模式
 * 是否 Lazy 初始化：是
 * 是否多线程安全：否
 * 缺点:线程不安全 可能导致建立多个对象
 */
public class SingletonOne {
    private static SingletonOne singletonOne;
    private SingletonOne(){};
    private static SingletonOne getInstance(){
        if(singletonOne == null)
            singletonOne = new SingletonOne();
        return singletonOne;
    }
}
