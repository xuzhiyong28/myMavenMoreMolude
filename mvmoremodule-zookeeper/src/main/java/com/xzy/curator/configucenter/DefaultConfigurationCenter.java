package com.xzy.curator.configucenter;

import com.google.common.collect.Maps;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://www.jianshu.com/p/7d324ac9cc64
public class DefaultConfigurationCenter implements ConfigurationCenter {

    private static final String CONFIGURATION_ROOT_PATH = "/configuration";
    private CuratorFramework client;
    private PathChildrenCache pathChildrenCache;
    private ExecutorService executor;
    private static final ConcurrentMap<String,String> configMap = new ConcurrentHashMap<>();

    public DefaultConfigurationCenter(String connectionString) {
        executor = Executors.newFixedThreadPool(2);
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        this.client = CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(50000)
                .retryPolicy(retryPolicy)
                .build();
        initCuratorClient();
    }


    private void initCuratorClient() {
        try {
            client.start();
            Stat stat = client.checkExists().forPath(CONFIGURATION_ROOT_PATH);
            if (null == stat) {
                client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT);
            }
            pathChildrenCache = new PathChildrenCache(client, CONFIGURATION_ROOT_PATH, true);
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                    String eventType = event.getType().name();
                    String path = event.getData().getPath();
                    String key = path.substring(path.lastIndexOf("/") + 1);
                    String data = null != event.getData() ? new String(event.getData().getData(), "UTF-8") : "";
                    //如果是添加子节点的操作
                    //PathChildrenCacheEvent.Type.CHILD_ADDED
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            //todo
                            break;
                        case CHILD_REMOVED:
                            //todo
                            break;
                    }
                }
            }, executor);
            pathChildrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void addConfiguration(String key, String value) throws Exception {

    }

    @Override
    public void deleteConfiguration(String key) throws Exception {

    }

    @Override
    public void updateConfiguration(String key, String value) throws Exception {

    }

    @Override
    public String getConfiguration(String key) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> getAllConfiguration() throws Exception {
        if(!configMap.isEmpty()){
            return configMap;
        }
        List<String> childPaths = client.getChildren().forPath(CONFIGURATION_ROOT_PATH);
        Map<String, String> configs = Maps.newHashMap();
        if(null != childPaths && childPaths.isEmpty()){
            for(String childPath : childPaths){

            }
        }
        return null;
    }
}
