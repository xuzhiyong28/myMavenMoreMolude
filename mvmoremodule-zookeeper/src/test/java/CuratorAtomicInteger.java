import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/***
 * cuartor分布式计数器 涉及到的类：SharedCount，SharedCountReader，SharedCountListener(监听器)
 * 看下面的代码发现SharedCount不像java自带的AtomicInteger类(采用自旋方式，设置不成功会再次设置)，但是提供了trySetCount，我们可以使用循环在不成功的情况下再次执行
 */
public class CuratorAtomicInteger {
    private static final int QTY = 100;
    private static final String PATH = "/examples/counter";

    @Test
    public void test() throws Exception {
        final Random rand = new Random();
        CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(2000, 3));
        cf.start();
        SharedCount baseCount = new SharedCount(cf, PATH, 1); //初始的计数为0
        baseCount.addListener(new MySharedCountListener());
        baseCount.start();
        List<SharedCount> examples = Lists.newArrayList();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < QTY; ++i) {
            final SharedCount count = new SharedCount(cf,PATH,1);
            examples.add(count);
            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    count.start();
                    Thread.sleep(rand.nextInt(10000));
                    //trySetCount去设置计数器。 第一个参数提供当前的VersionedValue,如果期间其它client更新了此计数值， 你的更新可能不成功，
                    //但是这时你的client更新了最新的值，所以失败了你可以尝试再更新一次。 而setCount是强制更新计数器的值
                    //System.out.println("Increment:" + count.trySetCount(count.getVersionedValue(), count.getCount() + rand.nextInt(10)));
                    while(true){
                        //类似与自选锁，失败后不断尝试
                        if(count.trySetCount(count.getVersionedValue(), count.getCount() + 1)){
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
        for(int i = 0 ; i < QTY; ++i){
            examples.get(i).close();
        }
        System.out.println("最后的值 =" + baseCount.getVersionedValue().getValue());
        baseCount.close();
        cf.close();
    }

    /***
     * 监听器
     */
    public class MySharedCountListener implements SharedCountListener {

        @Override
        public void countHasChanged(SharedCountReader sharedCountReader, int newCount) throws Exception {
            System.out.println("Counter's value is changed to " + newCount);
        }

        @Override
        public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
            System.out.println("State changed: " + connectionState.toString());
        }
    }
}
