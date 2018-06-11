package com.mvmoremodulePattern.SingletonPattern;/**
 * Created by Administrator on 2018-04-26.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-04-26-9:42
 */
public class SingletonFour {
    private static volatile SingletonFour singletonFour;
    private SingletonFour(){};
    private static SingletonFour getSingleton(){
        if(singletonFour == null){  //第一次检查
            synchronized (SingletonFour.class){
                if(singletonFour == null) //第二次检查
                    singletonFour = new SingletonFour(); // 标注1
            }
        }
        return singletonFour;
    }
}


/***
 * 问题 ：
 *  1.为什么要两次检查 ？
 *      如果一次检查就是懒汉式线程安全单例模式了，这种模式每次都要执行synchronized，synchronized又会浪费开销。加了两次检查后，后面的检查到单例不为空了就
 *      直接返回单例对象，也不会走到里面的synchronized。但是这种判断在不加volatile会有问题，原因是标注1会发生重排序，
 *      另一个并发执行的线程B就有可能在判断instance不为null时，线程B接下来将访问instance所引用的对象，但此时这个对象可能还没有被A线程初始化
 *  2.两次检查的情况下，没有加volatile会有什么问题
 *      如问题1
 *  3.加了volatile 然后 加两次检查 最后的好处
 *      根据上面的问题加了volatile就可以解决重排序的问题和内存可见性问题
 * */