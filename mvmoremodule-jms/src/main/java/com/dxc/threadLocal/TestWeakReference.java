package com.dxc.threadLocal;

import java.lang.ref.WeakReference;

public class TestWeakReference {

    private static ThreadModel testThreadModel = new ThreadModel("xuzhiyong");

    public static void main(String[] args) {
        TestWeakReference testWeakReference = new TestWeakReference();
        testWeakReference.test3();
    }


    public void test1(){
        Salad salad = new Salad(new ThreadModel("许志勇"));
        System.out.println("object:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("对象已经被清除");
        }
    }

    public void test2(){
        ThreadLocal<ThreadModel> local = new ThreadLocal<>();
        local.set(new ThreadModel("xuzy"));
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(local.get() == null){
            System.out.println("对象已经被清除");
        }
    }


    public void test3(){
        Salad salad = new Salad(testThreadModel);
        System.out.println("object:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("对象已经被清除");
        }
    }


    /***
     * 弱引用对象
     */
    static class Salad extends WeakReference<ThreadModel> {
        public Salad(ThreadModel threadModel) {
            super(threadModel);
        }
    }
}
