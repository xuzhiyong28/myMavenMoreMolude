package com.dxc.sleep;/**
 * Created by Administrator on 2018-05-31.
 */

/**
 * @author xuzhiyong
 * @createDate 2018-05-31-17:44
 */
public class Main1 {
    public static void main(String agrs[]){
        Thread thread = new Thread(new MyThread());
        System.out.println("main - begin");
        thread.start();
        System.out.println("main - end");
    }

    static class MyThread implements Runnable{
        @Override
        public void run() {
            try{
                System.out.println("MyThread - begin");
                Thread.sleep(20000);
                System.out.println("MyThread - end");
            }catch (Exception e){

            }
        }
    }
}
