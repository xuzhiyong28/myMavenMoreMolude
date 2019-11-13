package com.xzy.balance.server;

import com.xzy.balance.model.ServerData;
import org.apache.curator.framework.CuratorFramework;

public class ZooKeeperRegistContext {
    /***
     * 注册的地址
     */
    private String path;
    /***
     * zk连接器
     */
    private CuratorFramework cf;
    /***
     * 注册的服务器信息
     */
    private ServerData data;

    public ZooKeeperRegistContext(String path, CuratorFramework cf, ServerData data) {
        this.path = path;
        this.cf = cf;
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public CuratorFramework getCf() {
        return cf;
    }

    public void setCf(CuratorFramework cf) {
        this.cf = cf;
    }

    public ServerData getData() {
        return data;
    }

    public void setData(ServerData data) {
        this.data = data;
    }
}
