package lock.curator;

import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

public class SharedLock extends AbstractZookeeperLock<InterProcessSemaphoreMutex> {
    private final String lockKey = "shared-lock";

    /**
     * Shared Lock
     * <p>
     * 获取排他锁，阻塞直到可用，单实例{@code InterProcessSemaphoreMutex} <br>
     * interProcessMutex {@link InterProcessSemaphoreMutex#InterProcessSemaphoreMutex} : <ul>
     * <li>参数1: zk连接上下文{@link AbstractZookeeperLock#curatorFramework} </li>
     * <li>参数2: zk锁路径，路径相同锁相同 </li>
     * </ul>
     *
     * @param path
     * @throws Exception 抛出异常时，锁不在持有，获取锁失败，谨慎处理业务
     */
    public void acquire(String path) {
        try {
            super.lock(getSharedLock(path));
        } catch (Exception e) {
            throw new IllegalStateException("获取共享锁失败=>" + e);
        }
    }

    /**
     * Shared Lock
     *
     * @param time 获取互斥锁：阻止直到可用或给定的时间到期
     * @return 如果获得了互斥锁，则返回true，否则返回false
     * @throws Exception 抛出异常时，锁不在持有，获取锁失败，谨慎处理业务
     */
    public boolean acquire(String path, long time) throws Exception {
        try {
            return super.lock(getSharedLock(path), time);
        } catch (Exception e) {
            throw new IllegalStateException("获取共享锁失败=>" + e);
        }
    }

    public void release(String path) throws Exception {
        super.unlock(locks.get(lockKey + path));
    }

    private InterProcessSemaphoreMutex getSharedLock(String path) {
        String key = lockKey + "_" + path;
        InterProcessSemaphoreMutex interProcessSemaphoreMutex = null;
        if (locks.containsKey(key)) {
            interProcessSemaphoreMutex = locks.get(key);
        } else {
            interProcessSemaphoreMutex = new InterProcessSemaphoreMutex(curatorFramework, path);
            locks.put(key, interProcessSemaphoreMutex);
        }
        return interProcessSemaphoreMutex;
    }
}
