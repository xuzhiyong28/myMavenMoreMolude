package xzy.dxc;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {


    /***
     * 测试线程池正在执行的任务有哪些，队列是哪些
     *
     */
    @Test
    public void test0() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 20, 60L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        for (int i = 0; i < 200; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(RandomUtils.nextInt(10,20));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + "执行完成");
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(String.format(
                            "活动的任务数量：%s,完成的任务数量：%s,任务队列中的任务数量：%s,核心线程数：%s",
                            threadPoolExecutor.getActiveCount(),
                            threadPoolExecutor.getCompletedTaskCount(),
                            threadPoolExecutor.getQueue().size(),
                            threadPoolExecutor.getPoolSize()
                            ));

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //修改核心线程数
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadPoolExecutor.setCorePoolSize(10);
            }
        }).start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }








}
