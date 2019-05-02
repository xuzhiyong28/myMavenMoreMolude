package otherclass;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * LeaderSelector适配器
 *
 */
public class LeaderSelectorAdapter extends LeaderSelectorListenerAdapter implements Closeable {
    private final String name;
    private final LeaderSelector leaderSelector;
    private final AtomicInteger leaderCount = new AtomicInteger();

    public LeaderSelectorAdapter(CuratorFramework client, String path, String name) {
        this.name = name;
        leaderSelector = new LeaderSelector(client, path, this);
        leaderSelector.autoRequeue();
    }

    /***
     * 获取领导权
     * @param clent
     * @throws Exception
     * takeLeadership()回调方法中编写获得Leader权利后的业务处理逻辑
     */
    @Override
    public void takeLeadership(CuratorFramework clent) throws Exception {
        final int waitSeconds = (int) (5 * Math.random()) + 1;
        System.out.println(name + " is now the leader. Waiting " + waitSeconds + " seconds...");
        System.out.println(name + " has been leader " + leaderCount.getAndIncrement() + " time(s) before.");
        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(waitSeconds));
        }catch (Exception e){
            System.err.println(name + " was interrupted.");
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(name + " relinquishing leadership.\n");
        }
    }

    public void start() throws IOException{
        leaderSelector.start();
    }

    public void close() throws IOException{
        leaderSelector.close();
    }
}
