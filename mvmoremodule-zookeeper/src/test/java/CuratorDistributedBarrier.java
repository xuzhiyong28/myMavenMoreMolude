import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * 分布式屏障 类似java的CyclicBarrier
 *
 * */
public class CuratorDistributedBarrier {
    private static final int QTY = 5;
    private static final String PATH = "/examples/barrier";

    @Test
    public void test() throws Exception {
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        cf.start();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        DistributedBarrier controlBarrier = new DistributedBarrier(cf, PATH);
        controlBarrier.setBarrier(); //设置栅栏
        for (int i = 0; i < QTY; ++i) {
            final DistributedBarrier barrier = new DistributedBarrier(cf, PATH);
            final  int index = i ;
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    Thread.sleep((long) (3 * Math.random()));
                    System.out.println("Client #" + index + " waits on Barrier");
                    barrier.waitOnBarrier(); //等待放行
                    System.out.println("Client #" + index + " begins");
                    return null;
                }
            };
            service.submit(task);
        }
        Thread.sleep(10000);
        System.out.println("all Barrier instances should wait the condition");
        controlBarrier.removeBarrier(); //删除栅栏，其他线程就会放行
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        Thread.sleep(20000);
    }
}
