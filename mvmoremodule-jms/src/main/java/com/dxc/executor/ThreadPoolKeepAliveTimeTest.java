package com.dxc.executor;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.concurrent.*;

/****
 * 线程的空闲时间到底是什么
 * keepAliveTime表示线程数大于核心线程数时，此为终止前多余的空闲线程等待新任务的最长时间
 * 例如下面的例子 核心线程数是3 队列是5 那当10个线程进来的话 3个在核心线程执行，5个在队列。其他的2个应该队列满了会启动额外线程执行，核心线程的任务执行完后不会关闭，但是额外线程的任务
 * 做完后根据keepAliveTime这个值来控制关闭时间
 * Executors.newCachedThreadPool()的核心线程数为0，keepAliveTime为60，最大线程数为无限。所以任务做完后60秒 即使不用shutdown(),线程池也会自动关闭
 */
public class ThreadPoolKeepAliveTimeTest {
    //核心线程数
    private static int corePoolSize = 3;
    // 最大线程数量
    private static int maxPoolSize = 5;
    // 线程存活时间：当线程数量超过corePoolSize时，10秒钟空闲即关闭线程
    private static int keepAliveTime = 10000;

    private static ThreadPoolExecutor threadPoolExecutor = null;

    static {
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(5));
    }

    private static ExecutorService cachedThreadPool = null;
    static {
        cachedThreadPool = Executors.newCachedThreadPool();
    }

    public static void main(String[] args) {
        try{
            for(int i = 0 ; i < 20 ; i++){
                System.out.println("=========第" + i + "次");
                threadPoolExecutor.execute(new Mycallable());
                System.out.println("线程池中正在执行的线程数量：" + threadPoolExecutor.getPoolSize());
                System.out.println("线程池缓存的任务队列数量：" + threadPoolExecutor.getQueue().size());
                //cachedThreadPool.execute(new Mycallable());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }
    }


    public static class Mycallable implements Runnable {
        @Override
        public void run() {
            System.out.println("!!!!!");
        }
    }

}
