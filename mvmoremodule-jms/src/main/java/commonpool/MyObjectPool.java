package commonpool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/***
 * PoolModel的对象池
 */
public class MyObjectPool extends Pool<PoolModel> {

    public MyObjectPool(GenericObjectPoolConfig poolConfig, PooledObjectFactory<PoolModel> factory){
        initPool(poolConfig, factory);
    }
}
