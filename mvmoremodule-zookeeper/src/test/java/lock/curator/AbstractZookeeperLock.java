package lock.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public abstract class AbstractZookeeperLock<E extends InterProcessLock> implements Lock{
    public static CuratorFramework curatorFramework;
    public final ConcurrentHashMap<String,E> locks = new ConcurrentHashMap<>();

    public AbstractZookeeperLock(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000000, 3);
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
    }

    @Override
    public void lock(InterProcessLock interProcessLock) throws Exception {
        try {
            interProcessLock.acquire();
            lockConnectionStateListenable();
        } catch (Exception e) {
            throw new IllegalStateException("锁丢失=>" + e);
        }
    }

    @Override
    public boolean lock(InterProcessLock interProcessLock, long milliseconds) throws Exception {
        boolean state;
        try {
            state = interProcessLock.acquire(milliseconds, TimeUnit.MILLISECONDS);
            lockConnectionStateListenable();
        } catch (Exception e) {
            throw new IllegalStateException("锁丢失=>" + e);
        }
        return state;
    }

    @Override
    public void unlock(InterProcessLock interProcessLock) throws Exception {
        try {
            interProcessLock.release();
        } catch (Exception e) {
            throw new IllegalStateException("锁丢失=>" + e);
        }
    }

    /**
     * 监听zk连接状态.
     */
    public void lockConnectionStateListenable() throws Exception{
        curatorFramework.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            // ConnectionStateListener并监视SUSPENDED和LOST状态更改。如果报告“挂起”状态，则不能确定是否仍然保持锁定状态，
            // 除非您随后收到“重新连接”状态。如果报告失去状态，则确定您不再持有该锁
            public void stateChanged(CuratorFramework client, ConnectionState state) {
                if (state == ConnectionState.LOST) {
                    // TODO 考虑删除锁实例
                    throw new IllegalStateException("会话超时锁丢失=>");
                }
            }
        });
    }
}
