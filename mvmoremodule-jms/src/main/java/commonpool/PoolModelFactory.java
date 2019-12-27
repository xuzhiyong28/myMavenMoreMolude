package commonpool;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/***
 * 对象池工厂
 */
public class PoolModelFactory implements PooledObjectFactory<PoolModel> {
    /***
     * 创建对象
     * @return
     * @throws Exception
     */
    @Override
    public PooledObject<PoolModel> makeObject() throws Exception {
        System.out.println("====创建对象===");
        return new DefaultPooledObject<>(new PoolModel(RandomUtils.nextInt(0, 100), "xuzy"));
    }

    /***
     * 销毁
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void destroyObject(PooledObject<PoolModel> pooledObject) throws Exception {
        //这里销毁会从池里移除，我们这里需要做一些移除后的操作。例如redis需要关闭连接
        System.out.println("====销毁对象===");
    }

    /***
     * 验证
     * @param pooledObject
     * @return
     */
    @Override
    public boolean validateObject(PooledObject<PoolModel> pooledObject) {
        //用来校验对象是否符合
        PoolModel poolModel = pooledObject.getObject();
        System.out.println("====校验对象是否符合===");
        return true;
    }

    /***
     * 激活
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void activateObject(PooledObject<PoolModel> pooledObject) throws Exception {
        //还不知道有什么用
    }

    /***
     * 卸载
     * @param pooledObject
     * @throws Exception
     */
    @Override
    public void passivateObject(PooledObject<PoolModel> pooledObject) throws Exception {
        //不知道干嘛用
    }
}
