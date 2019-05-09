import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***
 * cuartor分布式计数器
 get(): 获取当前值
 increment()： 加一
 decrement(): 减一
 add()： 增加特定的值
 subtract(): 减去特定的值
 trySet(): 尝试设置计数值
 forceSet(): 强制设置计数值
 */
public class CuratorAtomicLong {
    private static final int QTY = 100;
    private static final String PATH = "/examples/atomicLong";

    //你必须检查返回结果的succeeded()， 它代表此操作是否成功。 如果操作成功， preValue()代表操作前的值， postValue()代表操作后的值
    @Test
    public void test() throws Exception {
        List<DistributedAtomicLong> examples = Lists.newArrayList();
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
        cf.start();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < QTY; ++i) {
            final int index = i;
            final DistributedAtomicLong count = new DistributedAtomicLong(cf,PATH,new RetryNTimes(10,100));
            examples.add(count);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    AtomicValue<Long> value = count.increment(); //+1
                    System.out.println("succeed:" + value.succeeded() + "..............." + index);
                    if(value.succeeded()){
                        System.out.println("Increment: from " + value.preValue() + " to " + value.postValue());
                    }
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(60);
        DistributedAtomicLong currentCount = new DistributedAtomicLong(cf,PATH,new RetryNTimes(10,100));
        System.out.println(currentCount.get().postValue());
        System.out.println("job end!!!");
    }

    @Test
    public void test2() throws Exception {
        List<DistributedAtomicLong> examples = Lists.newArrayList();
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
        cf.start();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < QTY; ++i) {
            final int index = i;
            final DistributedAtomicLong count = new DistributedAtomicLong(cf,PATH,new RetryNTimes(10,100));
            examples.add(count);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    //进行重复加1 如果设置不成功重新进行设置
                    AtomicValue<Long> value = count.increment(); //+1
                    while(value.succeeded() != true){
                        value = count.increment();
                        if(value.succeeded()){
                            System.out.println("Increment: from " + value.preValue() + " to " + value.postValue());
                            break;
                        }
                    }
                    return null;
                }
            };
            service.submit(task);
        }
        service.shutdown();
        service.awaitTermination(10, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(60);
        DistributedAtomicLong currentCount = new DistributedAtomicLong(cf,PATH,new RetryNTimes(10,100));
        System.out.println(currentCount.get().postValue());
        System.out.println("job end!!!");
    }




}
