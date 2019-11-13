package com.xzy.balance.impl;

import com.xzy.balance.server.RegistProvider;
import com.xzy.balance.server.ZooKeeperRegistContext;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

public class DefaultRegistProvider implements RegistProvider {

    // 在zookeeper中创建临时节点并写入信息
    @Override
    public void regist(Object context) throws Exception {
        // Server在zookeeper中注册自己，需要在zookeeper的目标节点上创建临时节点并写入自己
        // 将需要的以下3个信息包装成上下文传入
        // 1:path
        // 2:zkClient
        // 3:serverData
        ZooKeeperRegistContext registContext = (ZooKeeperRegistContext) context;
        String path = registContext.getPath();
        CuratorFramework cf = registContext.getCf();
        //注册临时节点
        try {
            System.out.println("path = " + path);
            cf.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path, SerializationUtils.serialize(registContext.getData()));
        } catch (Exception e) {
            System.out.println("注册失败 ：" + e.getMessage());
        }

    }

    @Override
    public void unRegist(Object context) throws Exception {
        return;
    }
}
