package com.xzy.curator.configucenter;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://www.jianshu.com/p/7d324ac9cc64
public class DefaultConfigurationCenter {

    private static final String CONFIGURATION_ROOT_PATH = "/configuration";
    private static final String connectionString = "localhost:2181";
    private static final CuratorFramework client;
    private static PathChildrenCache pathChildrenCache;
    private static ExecutorService executors = Executors.newFixedThreadPool(2);
    private static final ConcurrentMap<String,String> configMap = new ConcurrentHashMap<>();

    static {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(50000)
                .retryPolicy(retryPolicy)
                .build();
        initCuratorClient();
    }

    /***
     * 初始化连接
     */
    public static void initCuratorClient() {
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
                    String path = event.getData().getPath();
                    String key = path.substring(path.lastIndexOf("/") + 1);
                    String data = null != event.getData() ? new String(event.getData().getData(), "UTF-8") : "";
                    //如果是添加子节点的操作
                    //PathChildrenCacheEvent.Type.CHILD_ADDED
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            //todo 添加子节点
                            reloadConfigMap(key,data);
                            break;
                        case CHILD_REMOVED:
                            //todo 删除子节点
                            removeConfigMap(key,data);
                            break;
                        case CHILD_UPDATED:
                            //todo 修改子节点
                            reloadConfigMap(key,data);

                    }
                }
            }, executors);
            pathChildrenCache.start();
            //初始化数据
            initConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 关闭zk连接 - 一般在jvm退出时候关闭连接
     */
    public static void close(){
        if(pathChildrenCache != null){
            CloseableUtils.closeQuietly(pathChildrenCache);
        }
        if(client != null){
            CloseableUtils.closeQuietly(client);
        }
        if (null != executors && !executors.isShutdown()) {
            executors.shutdown();
        }
    }

    /***
     * 添加节点
     * @param key
     * @param value
     * @throws Exception
     */
    public static void addConfiguration(String key, String value) throws Exception {
        if(!checkChildPathExists(key)){
            client.create().withMode(CreateMode.PERSISTENT).forPath(concatKey(key),value.getBytes("UTF-8"));
        }
    }


    /***
     * 更新节点
     * @param key
     * @param value
     * @throws Exception
     */
    public static void updateConfiguration(String key, String value) throws Exception {
        if(checkChildPathExists(key)){
           client.setData().forPath(concatKey(key),value.getBytes("UTF-8"));
        }
    }

    /***
     * 删除节点
     * @param key
     * @param value
     * @throws Exception
     */
    public static void deleteConfiguration(String key, String value) throws Exception {
        if (checkChildPathExists(key)) {
            client.delete().forPath(concatKey(key));
        }
    }


    /****
     * 判断节点是否存在
     * @param key
     * @return
     * @throws Exception
     */
    private static boolean checkChildPathExists(String key) throws Exception {
        Stat stat = client.checkExists().forPath(concatKey(key));
        return null != stat ? true : false;
    }


    /***
     * 初始化配置 - 将zk的配置加载到configMap中
     * @throws Exception
     */
    public static void initConfiguration() throws Exception {
        List<String> childPaths = client.getChildren().forPath(CONFIGURATION_ROOT_PATH);
        if(null != childPaths && !childPaths.isEmpty()){
            for(String childPath : childPaths){
                String key = childPath.substring(childPath.indexOf("/") + 1);
                String value = new String(client.getData().forPath(concatKey(childPath)),"UTF-8");
                configMap.put(key,value);
            }
        }
        System.out.println(configMap);
    }

    private static String concatKey(String key) {
        //concat -- 拼接
        return CONFIGURATION_ROOT_PATH.concat("/").concat(key);
    }


    private static void reloadConfigMap(String key , String value){
        configMap.put(key,value);
    }

    private static void removeConfigMap(String key , String value){
        if(configMap.containsKey(key)){
            configMap.remove(key);
        }
    }
}
