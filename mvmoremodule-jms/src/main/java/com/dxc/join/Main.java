package com.dxc.join;/**
 * Created by Administrator on 2018-06-01.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-06-01-13:43
 */
public class Main {
    public static void main(String[] args){
        try{
            MyThread myThread = new MyThread();
            myThread.start();
            myThread.join();
            System.out.println("必须等myThread执行完才执行");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static class MyThread extends Thread{
        @Override
        public void run() {
            int seconValue = (int)(Math.random() * 10000);
            System.out.println(seconValue);
            try {
                Thread.sleep(seconValue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
