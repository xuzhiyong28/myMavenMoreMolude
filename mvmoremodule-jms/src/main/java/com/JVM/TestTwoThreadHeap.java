package com.JVM;

import com.google.common.collect.Lists;

import java.util.List;

/***
 * https://mp.weixin.qq.com/s/uiBDjTrs51Qja-Mq5G6l2Q
 */
public class TestTwoThreadHeap {
    public static void main(String[] agrs){
        new Thread(new MyThread0(),"thread-0").start();
        new Thread(new MyThread1(),"thread-1").start();
    }

    static class MyThread0 extends Thread{
        @Override
        public void run() {
            List<byte[]> bytesList = Lists.newArrayList();
            while(true){
                try {
                    bytesList.add(new byte[1024]);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class MyThread1 extends Thread{
        @Override
        public void run() {
            while(true){
                List<byte[]> bytesList = Lists.newArrayList();
                try{
                    bytesList.add(new byte[1024]);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
