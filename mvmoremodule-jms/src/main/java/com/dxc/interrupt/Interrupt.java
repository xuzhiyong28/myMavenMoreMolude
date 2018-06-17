package com.dxc.interrupt;/**
 * Created by Administrator on 2018-06-12.
 */

import java.util.concurrent.TimeUnit;

/**
 * @author xuzhiyong
 * @createDate 2018-06-12-9:34
 * 线程调用interrupt请求中断。然后线程里面通过isInterrupted()来判断中断标志并中断
 */
public class Interrupt {
    public static void main(String[] agrs) {
        try{
            MyThread myThread = new MyThread();
            myThread.start();
            TimeUnit.SECONDS.sleep(1); //休眠两秒
            myThread.interrupt(); //请求中断
        }catch (Exception e){
            System.out.println("main catch");
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 500000; i++) {
                if (this.isInterrupted()) {
                    System.out.println("should be stopped and exit");
                    break;
                }
                System.out.println("i=" + (i + 1));
            }
            System.out.println("this line is also executed. thread does not stopped：许志勇"); //尽管线程被中断,但并没有结束运行。这行代码还是会被执行
        }
    }
}
