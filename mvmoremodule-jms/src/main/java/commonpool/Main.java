package commonpool;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class Main {
    public static void main(String[] args) {
        //对象池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxTotal(100);
        Pool<PoolModel> myObjectPool = new MyObjectPool(poolConfig, new PoolModelFactory());
        PoolModel poolModel = myObjectPool.getResource();
        System.out.println(poolModel);
    }
}
