package xzy.dxc;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.concurrent.*;

public class ThreadPoolTest {


    /***
     * 测试线程池正在执行的任务有哪些，队列是哪些
     *
     */
    @Test
    public void test0() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(RandomUtils.nextInt(10,20));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getId() + "执行完成");
            });
        }
        new Thread(() -> {
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
        }).start();
        //修改核心线程数
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadPoolExecutor.setCorePoolSize(5);
            System.out.println("设置核心线程数完成，长度为5");
        }).start();
        TimeUnit.SECONDS.sleep(100);
        System.out.println(threadPoolExecutor.getCorePoolSize());
    }

    /***
     * submit 不会打印异常 只有在 Future.get() 才会打印异常
     * execute 会打印异常
     */
    @Test
    public void testError() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(() -> {
            System.out.println("线程池源码解析-execute");
            int i = 1 / 0;
        });
        threadPoolExecutor.submit(() -> {
            System.out.println("线程池源码解析-submit");
            int i = 1 / 0;
        });
        Future future = threadPoolExecutor.submit(() -> {
            System.out.println("线程池源码解析-submit-future");
            int i = 1 / 0;
        });
        future.get();
        TimeUnit.SECONDS.sleep(10);
    }


    @Test
    public void testCommon() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程池源码解析");
            }
        });
    }

    @Test
    public void testCacheThreadPool(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> System.out.println("线程池源码解析-newCachedThreadPool"));
    }


    @Test
    public void executorPolicyTest(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                1L,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.AbortPolicy());
        ThreadPoolExecutor executor2 = new ThreadPoolExecutor(1,
                1,
                1L,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(1),
                new RejectedExecutionHandler(){
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                    }
                });

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }








}
