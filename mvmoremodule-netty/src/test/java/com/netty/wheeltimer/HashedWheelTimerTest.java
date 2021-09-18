package com.netty.wheeltimer;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/***
 * 时间轮
 */
public class HashedWheelTimerTest {

    public static void main(String[] args) throws InterruptedException {
        MyTimerTask timerTask = new MyTimerTask(true);
        Timer timer = new HashedWheelTimer();
        timer.newTimeout(timerTask, 5, TimeUnit.SECONDS); //后端这里就会默认5秒后去执行
        TimeUnit.SECONDS.sleep(10); // 进行阻塞观察结果
    }

    static class MyTimerTask implements TimerTask {
        boolean flag;
        public MyTimerTask(boolean flag) {
            this.flag = flag;
        }
        @Override
        public void run(Timeout timeout) throws Exception {
            System.out.println("要去数据库删除订单了。。。。");
            this.flag = false;
        }
    }
}
