package com.dxc.isAlive;/**
 * Created by Administrator on 2018-05-31.
 */

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-05-31-17:27
 * isAlive() 判断对象是否存活 -- 准备和运行状态都算存活
 */
public class Main {

    public static void main(String agrs[]) throws InterruptedException {
        MyThead myThead = new MyThead();
        System.out.println("begin = " + myThead.isAlive());
        myThead.start();
        //TimeUnit.SECONDS.sleep(2);
        System.out.println("end = " + myThead.isAlive());
    }

    static class MyThead extends Thread{
        @Override
        public void run() {
            System.out.println("run = " + this.isAlive());
        }
    }

}
