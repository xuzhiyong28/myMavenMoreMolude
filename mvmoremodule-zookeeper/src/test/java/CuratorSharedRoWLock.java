import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;
import otherclass.FakeLimitedResource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * 读写锁 采用InterProcessReadWriteLock InterProcessMutex 实现
 */
public class CuratorSharedRoWLock {

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY;
    private static final String PATH = "/examples/locks";

    @Test
    public void test() throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        try {
            for (int i = 0; i < QTY; ++i) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
                        try {
                            cf.start();
                            final ReentrantReadWriteLockDemo example = new ReentrantReadWriteLockDemo(cf, PATH, resource, "Client_" + index);
                            for (int j = 0; j < REPETITIONS; ++j) {
                                example.doWork(10, TimeUnit.SECONDS);
                            }
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
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {

        }
    }


    public class ReentrantReadWriteLockDemo {
        private final InterProcessReadWriteLock lock;
        private final InterProcessMutex readLock;
        private final InterProcessMutex writeLock;
        private final FakeLimitedResource resource;
        private final String clientName;

        public ReentrantReadWriteLockDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
            this.resource = resource;
            this.clientName = clientName;
            lock = new InterProcessReadWriteLock(client, lockPath);
            readLock = lock.readLock();
            writeLock = lock.writeLock();
        }

        public void doWork(long time, TimeUnit unit) throws Exception {
            if (!writeLock.acquire(time, unit)) {
                throw new IllegalStateException(clientName + " 不能得到写锁");
            }
            System.out.println(clientName + " 已得到写锁");
            if (!readLock.acquire(time, unit)) {
                throw new IllegalStateException(clientName + " 不能得到读锁");
            }
            System.out.println(clientName + " 已得到读锁");
            try {
                resource.use();
                Thread.sleep(1000);
            } finally {
                System.out.println(clientName + " 释放读写锁");
                readLock.release();
                writeLock.release();
            }
        }
    }

}
