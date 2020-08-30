package lock.curator;

import org.apache.curator.framework.recipes.locks.InterProcessLock;

/***
 * https://blog.csdn.net/hatsune_miku_/article/details/79234079
 */
public interface Lock {
    /**
     * 获取锁 - 根据接口子类推断出当前锁类型
     *
     * @see org.apache.curator.framework.recipes.locks.InterProcessMultiLock
     * @see org.apache.curator.framework.recipes.locks.InterProcessMutex
     * @see org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex
     * @param interProcessLock
     * @throws Exception
     */
    void lock(InterProcessLock interProcessLock) throws Exception;

    /**
     * 获取锁 - 阻止直到可用或给定的时间到期
     *
     * @param interProcessLock
     * @param milliseconds
     * @throws Exception
     */
    boolean lock(InterProcessLock interProcessLock, long milliseconds) throws Exception;


    /**
     * 释放锁 - 根据锁接口子类推断出当前锁类型
     *
     * @see org.apache.curator.framework.recipes.locks.InterProcessMultiLock
     * @see org.apache.curator.framework.recipes.locks.InterProcessMutex
     * @see org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex
     * @param interProcessLock
     * @throws Exception
     */
    void unlock(InterProcessLock interProcessLock) throws Exception;
}
