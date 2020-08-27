package com.dxc.interrupt;

/***
 * 使用 interrupt() + InterruptedException 来中断线程
 */
public class Interrupt2 {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread(), "testThread");
        thread.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        MyThread.on = true;
        thread.interrupt();
    }


    static class MyThread extends Thread {
        private volatile static boolean on = false;
        @Override
        public void run() {
            while (!on) {
                try {
                    System.out.println("begin Sleep");
                    Thread.sleep(10000000);
                    System.out.println("end Sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Oh, myGod!");
            }
        }
    }
}
