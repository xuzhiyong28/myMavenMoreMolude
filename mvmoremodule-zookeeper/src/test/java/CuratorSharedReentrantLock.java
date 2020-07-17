import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;
import otherclass.FakeLimitedResource;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * Curator提供InterProcessMutex类来实现可重入锁
 */
public class CuratorSharedReentrantLock {

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;
    private static final String PATH = "/examples/locks";


    @Test
    public void test0(){
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
        final InterProcessMutex lock = new InterProcessMutex(cf, "/curator/locks");
        List<Thread> threadList = Lists.newArrayList();
        cf.start();
        for(int i = 0 ; i < QTY ; i++){
            Thread t = new Thread(() -> {
                try{
                    System.out.println("线程开始阻塞 ：" + Thread.currentThread());
                    lock.acquire();
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("线程阻塞5秒后，继续执行：" + Thread.currentThread());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            threadList.add(t);
        }
        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }




    @Test
    public void test() {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        try {
            //开启5个线程，每个线程启动一个客户端进行抢锁
            for (int i = 0; i < QTY; i++) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() {
                        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
                        try {
                            cf.start();
                            final InterProcessMutexDemo example = new InterProcessMutexDemo(cf, PATH, resource, "Client_" + index);
                            for(int j = 0 ; j < REPETITIONS ; ++j){
                                example.doWork(10 , TimeUnit.SECONDS);
                            }
                            //example.doWork(10 , TimeUnit.SECONDS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            CloseableUtils.closeQuietly(cf);
                        }
                        return null;
                    }
                };
                service.submit(task);
            }
            service.shutdown();
            service.awaitTermination(10,TimeUnit.MINUTES);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public class InterProcessMutexDemo {
        private InterProcessMutex lock;
        private final FakeLimitedResource resource;
        private final String clientName;

        public InterProcessMutexDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
            this.resource = resource;
            this.clientName = clientName;
            this.lock = new InterProcessMutex(client, lockPath);
        }

        public void doWork(long time, TimeUnit timeUnit) throws Exception {
            if (!lock.acquire(time, timeUnit)) {
                throw new IllegalStateException(clientName + " could not acquire the lock");
            }
            try {
                System.out.println(clientName + " get the lock");
                resource.use();
            } finally {
                System.out.println(clientName + " releasing the lock");
                lock.release();
            }
        }
    }
}
