package com.mvmoremodulePattern.SingletonPattern;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * 基于类初始化的单例模式
 * @author xuzhiyong
 * @createDate 2018-04-26-9:46
 * 是否 Lazy 初始化：是
   是否多线程安全：是
 * 在IoDH中，我们在单例类中增加一个静态(static)内部类，在该内部类中创建单例对象，再将该单例对象通过getInstance()方法返回给外部使用。
 * 由于静态单例对象没有作为Singleton的成员变量直接实例化，因此类加载时不会实例化Singleton，第一次调用getInstance()时将加载内部类SingletonHolder，
 * 在该内部类中定义了一个static类型的变量instance，此时会首先初始化这个成员变量，由Java虚拟机来保证其线程安全性，确保该成员变量只能初始化一次。
 * 由于getInstance()方法没有任何线程锁定，因此其性能不会造成任何影响。通过使用IoDH，我们既可以实现延迟加载，又可以保证线程安全，不影响系统性能，
 * 不失为一种最好的Java语言单例模式实现方式**（其缺点是与编程语言本身的特性相关，很多面向对象语言不支持IoDH）
 */
public class SingletonFive {
    private SingletonFive(){}
    //创建一个内部静态类
    private static class SingletonHolder{
        private final static SingletonFive sinleton = new SingletonFive();
    }
    public static SingletonFive getSingleton(){
        return SingletonHolder.sinleton;
    }

}
