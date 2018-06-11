package com.dxc.lock;/**
 * Created by Administrator on 2018-06-01.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-8:57
 */
public class Main1 {
    public static void main(String agrs[]){
        MyService1 myService1 = new MyService1();
        MyThread1 myThread1 = new MyThread1(myService1);
        MyThread2 myThread2 = new MyThread2(myService1);
        myThread1.start();
        myThread2.start();
    }

    static class MyThread1 extends Thread{
        private MyService1 myService1;
        public MyThread1(MyService1 myService1){
            this.myService1 = myService1;
        }
        @Override
        public void run() {
           for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
                myService1.get();
           }
        }
    }

    static class MyThread2 extends Thread{
        private MyService1 myService1;
        public MyThread2(MyService1 myService1){
            this.myService1 = myService1;
        }
        @Override
        public void run() {
            for(int i = 0 ; i < Integer.MAX_VALUE ; i++){
                myService1.set();
            }
        }
    }
}
