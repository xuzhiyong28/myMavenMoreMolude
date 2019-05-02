import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.junit.Test;
import otherclass.LeaderSelectorAdapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/****
 * Curator 提供的两种选举机制
 */
public class CuratorLeader {
    public static final String PATH = "/francis/leader";
    private static final int CLIENT_QTY = 10;

    /***
     * LeaderLatch 方式的选举回选择一个后就一直是这个客户端是主，直到他挂了才会再次选举
     */
    @Test
    public void leaderLatchDemo() {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderLatch> examples = Lists.newArrayList();
        try {
            //模拟10个客户端选举
            for (int i = 0; i < CLIENT_QTY; i++) {
                CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(20000, 3));
                clients.add(cf);
                LeaderLatch latch = new LeaderLatch(cf, PATH, "Client_#_" + i);
                latch.addListener(new LeaderLatchListener() {
                    @Override
                    public void isLeader() {
                        System.out.println("I am Leader");
                    }

                    @Override
                    public void notLeader() {
                        System.out.println("I am not Leader");
                    }
                });
                examples.add(latch);
                cf.start(); //启动客户端
                latch.start();
            }
            Thread.sleep(10000);
            LeaderLatch currentLeader = null;
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
            currentLeader.close(); //模拟当前的客户端断开，之后会重新选举
            Thread.sleep(5000); //5秒钟后查看新的选举状态
            for (LeaderLatch latch : examples) {
                if (latch.hasLeadership()) {
                    currentLeader = latch;
                }
            }
            System.out.println("current leader is " + currentLeader.getId());
            System.out.println("release the leader " + currentLeader.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭客户端和latch
            for (LeaderLatch latch : examples) {
                if (null != latch.getState() && !latch.getState().name().equals("CLOSED")) {
                    CloseableUtils.closeQuietly(latch);
                }
            }
            for (CuratorFramework cf : clients) {
                CloseableUtils.closeQuietly(cf);
            }
        }
    }


    /***
     * LeaderSelector
     * 一旦选举出Leader，除非有客户端挂掉重新触发选举，否则不会交出领导权
     */
    @Test
    public void leaderSelectorDemo() {
        List<CuratorFramework> clients = Lists.newArrayList();
        List<LeaderSelectorAdapter> examples = Lists.newArrayList();
        try {
            for(int i = 0 ; i < CLIENT_QTY ; i++){
                CuratorFramework cf = CuratorFrameworkFactory.newClient("localhost:2181",new ExponentialBackoffRetry(20000,3));
                clients.add(cf);
                LeaderSelectorAdapter selectorAdapter = new LeaderSelectorAdapter(cf,PATH,"Client #" + i);
                examples.add(selectorAdapter);
                cf.start();
                selectorAdapter.start();
            }
            System.out.println("Press enter/return to quit\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {

        } finally {
            System.out.println("Shutting down...");
            for (LeaderSelectorAdapter exampleClient : examples) {
                CloseableUtils.closeQuietly(exampleClient);
            }
            for (CuratorFramework client : clients) {
                CloseableUtils.closeQuietly(client);
            }
        }
    }

}
