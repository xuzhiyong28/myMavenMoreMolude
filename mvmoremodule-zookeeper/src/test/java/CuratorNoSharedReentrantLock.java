import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;
import otherclass.FakeLimitedResource;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * urator提供InterProcessSemaphoreMutex类来实现不可重入锁
 */
public class CuratorNoSharedReentrantLock {

    private static final int QTY = 5;
    private static final int REPETITIONS = QTY * 10;
    private static final String PATH = "/examples/locks";


    @Test
    public void test() throws InterruptedException {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);
        try{
            for(int i = 0 ; i < QTY ; ++i){
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call(){
                        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(2000,3));
                        try{
                            cf.start();
                            final InterProcessSemaphoreMutexDemo example = new InterProcessSemaphoreMutexDemo(cf, PATH, resource, "Client " + index);
                            for(int j = 0 ; j < REPETITIONS ; ++j){
                                example.doWork(10,TimeUnit.SECONDS);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }finally {
                            CloseableUtils.closeQuietly(cf);
                        }
                        return null;
                    }
                };
                service.submit(task);
            }
            service.shutdown();
            service.awaitTermination(10,TimeUnit.MINUTES);
        }catch (Exception e){

        }finally {

        }
        Thread.sleep(Integer.MAX_VALUE);
    }


    public class InterProcessSemaphoreMutexDemo{
        private InterProcessSemaphoreMutex lock;
        private final FakeLimitedResource resource;
        private final String clientName;

        public InterProcessSemaphoreMutexDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
            this.resource = resource;
            this.clientName = clientName;
            this.lock = new InterProcessSemaphoreMutex(client,lockPath);
        }

        public void doWork(long time , TimeUnit unit) throws Exception{
            if(!lock.acquire(time, unit)){
                throw new IllegalStateException(clientName + " 不能得到互斥锁");
            }
            System.out.println(clientName + " 已获取到互斥锁");
            if(!lock.acquire(time, unit)){
                throw new IllegalStateException(clientName + " 不能得到互斥锁");
            }
            System.out.println(clientName + " 再次获取到互斥锁");
            try{
                System.out.println(clientName + " get the lock");
                resource.use();
            }finally {
                System.out.println(clientName + " releasing the lock");
                lock.release();
                lock.release();
            }
        }
    }

}
