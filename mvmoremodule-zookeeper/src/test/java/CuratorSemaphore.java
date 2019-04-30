import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreV2;
import org.apache.curator.framework.recipes.locks.Lease;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;
import otherclass.FakeLimitedResource;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

/***
 * Curator提供的信号量 涉及到的类InterProcessSemaphoreV2，Lease，SharedCountReader
 */
public class CuratorSemaphore {
    private static final int MAX_LEASE = 10;
    private static final String PATH = "/examples/locks";

    @Test
    public void test() throws Exception {
        //ExponentialBackoffRetry(int baseSleepTimeMs, int maxRetries)中,baseSleepTimeMs参数代表两次连接的等待时间,maxRetries参数表示最大的尝试连接次数
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
        FakeLimitedResource resource = new FakeLimitedResource();
        cf.start();
        InterProcessSemaphoreV2 semaphore = new InterProcessSemaphoreV2(cf, PATH, MAX_LEASE);
        Collection<Lease> leases = semaphore.acquire(5);//获取5个信号量
        System.out.println("get " + leases.size() + " leases");
        Lease lease = semaphore.acquire(); //获取1个信号量
        System.out.println("get another lease");

        resource.use();

        Collection<Lease> leases2 = semaphore.acquire(5,10, TimeUnit.SECONDS);
        System.out.println("Should timeout and acquire return " + leases2); //此处应该会超时返回空，因为上面已经获取了6个信号量了
        System.out.println("return one lease");
        semaphore.returnLease(lease);
        System.out.println("return another 5 leases");
        semaphore.returnAll(leases);

    }
}
