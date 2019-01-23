package com.dxc;

/****
 * 为啥要用volatile
 */
public class VolatileExample extends Thread{
    private static volatile boolean flag = false;
    public void run() {
        while (!flag){
        };
        System.out.println("停止了");
    }

    public static void main(String[] args) throws InterruptedException {
        new VolatileExample().start();
        Thread.sleep(100);
        flag = true;
    }
}
