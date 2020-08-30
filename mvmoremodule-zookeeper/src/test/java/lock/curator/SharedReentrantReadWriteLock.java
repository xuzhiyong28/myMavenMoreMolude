package lock.curator;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;

import java.util.concurrent.ConcurrentHashMap;

public class SharedReentrantReadWriteLock extends SharedReentrantLock {
    public static ConcurrentHashMap<String,Object> locks = new ConcurrentHashMap<>();
    private final String lockKey = "read-write-lock";
    private final String readLock = "read-lock";
    private final String writeLock = "write-lock";
    // 获取锁超时时间
    private final long time = 5000L;

    /**
     * Shared Reentrant Read Lock<ul>
     * <li>1. 获取可重入读锁，zk锁路径，路径相同锁相同，默认获取超时时间5000毫秒。</li>
     * <li>2. 实现细节{@link com.miku.common.util.lock.zookeeper.SharedReentrantLock#acquire(String, long)}。</li>
     * <li>3. 多个客户端读锁共享。</li>
     * </ul>
     *
     * @param var
     * @return  如果获得了读锁，则返回true，否则返回false
     * @throws IllegalStateException    读锁获取失败
     */
    public boolean readLock(String var) {
        try {
            return super.lock(getReadLock(var),time);
        } catch (Exception e) {
            throw new IllegalStateException("获取读锁失败=>" + e);
        }
    }

    public boolean readLock(String var,final long time) {
        InterProcessMutex interProcessMutex = getReadLock(var);
        try {

            return super.lock(getReadLock(var),time);
        } catch (Exception e) {
            throw new IllegalStateException("获取读锁失败=>" + e);
        }
    }

    /**
     * Shared Reentrant Write Lock
     * <li>1. 获取可重入写锁，zk锁路径，路径相同锁相同，默认获取超时时间5000毫秒。</li>
     * <li>2. 实现细节{@link com.miku.common.util.lock.zookeeper.SharedReentrantLock#acquire(String, long)}。</li>
     * <li>3. 写锁独占,多个客户端无法同时获取写锁，写锁可降级为读锁。</li>
     * </ul>
     *
     * @param var
     * @return
     */
    public boolean writeLock(String var) {
        InterProcessMutex interProcessMutex = getWriteLock(var);
        try {
            return super.lock(getWriteLock(var),time);
        } catch (Exception e) {
            throw new IllegalStateException("获取写锁失败=>" + e);
        }
    }

    public boolean writeLock(String var,final long time) {
        InterProcessMutex interProcessMutex = getWriteLock(var);
        try {
            return super.lock(getWriteLock(var),time);
        } catch (Exception e) {
            throw new IllegalStateException("获取写锁失败=>" + e);
        }
    }

    public void readRelease(String var) throws Exception {
        super.unlock(getReadLock(var));
    }

    public void writeRelease(String var) throws Exception {
        super.unlock(getWriteLock(var));
    }

    private InterProcessReadWriteLock getReadWriteLock(String var){
        String key = lockKey + var;
        InterProcessReadWriteLock interProcessReadWriteLock = null;
        if (locks.containsKey(key)){
            interProcessReadWriteLock = (InterProcessReadWriteLock) locks.get(key);
        } else {
            interProcessReadWriteLock = new InterProcessReadWriteLock(curatorFramework,var);
            locks.put(key,interProcessReadWriteLock);
        }
        return interProcessReadWriteLock;
    }

    private InterProcessMutex getReadLock(String var){
        String readLockKey = readLock + var;
        InterProcessMutex interProcessMutex = null;
        if (locks.containsKey(readLockKey)){
            interProcessMutex = (InterProcessMutex) locks.get(readLockKey);
        } else {
            interProcessMutex = getReadWriteLock(var).readLock();
            locks.put(readLockKey,interProcessMutex);
        }
        return interProcessMutex;
    }

    private InterProcessMutex getWriteLock(String var){
        String writeLockKey = writeLock + var;
        InterProcessMutex interProcessMutex = null;
        if (locks.containsKey(writeLockKey)){
            interProcessMutex = (InterProcessMutex) locks.get(writeLockKey);
        } else {
            interProcessMutex = getReadWriteLock(var).writeLock();
            locks.put(writeLockKey,interProcessMutex);
        }
        return interProcessMutex;
    }
}
