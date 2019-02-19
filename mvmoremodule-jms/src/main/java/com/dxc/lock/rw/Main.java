package com.dxc.lock.rw;/**
 * Created by Administrator on 2018-06-01.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-10:35
 * 写写，读写加锁 读读不加锁
 */
public class Main {
    public static void main(String[] args){
        RWService rwService = new RWService();
        MyThreadA myThreadA = new MyThreadA(rwService);
        MyThreadB myThreadB = new MyThreadB(rwService);
        myThreadA.start();
        myThreadB.start();
    }

    static class MyThreadA extends Thread{
        private RWService rwService;
        public MyThreadA(RWService rwService){
            this.rwService = rwService;
        }
        @Override
        public void run() {
            rwService.write();
        }
    }

    static class MyThreadB extends Thread{
        private RWService rwService;
        public MyThreadB(RWService rwService){
            this.rwService = rwService;
        }
        @Override
        public void run() {
            rwService.read();
        }
    }
}
