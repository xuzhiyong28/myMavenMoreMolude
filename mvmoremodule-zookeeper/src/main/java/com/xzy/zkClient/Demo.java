package com.xzy.zkClient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @Auther: xuzy
 * @Date: 2018/7/28 16:38
 * @Description:
 */
public class Demo {
    private ZkClient zkClient ;
    private static final String _NODE_ = "/node_zkClient";
    public static void main(String agrs[]) throws InterruptedException {
        Demo demo = new Demo();
        demo.connection();
        demo.subscribeData();
    }


    public void connection(){
        zkClient = new ZkClient("localhost:2181",5000,5000, new SerializableSerializer());
    }

    public void createNode(){
        String path = zkClient.create(_NODE_, new User(1,"xuzhiyong"),CreateMode.PERSISTENT);
        System.out.println("path = " + path);
    }

    public void getNode(){
        Stat stat = new Stat();
        User user = zkClient.readData(_NODE_ , stat);
        if(user == null){
            System.out.println("Node doesn't exist!");
        }else{
            System.out.println(user);
            System.out.println(stat);
        }
    }

    public void getChildren(){
        List<String> children_list = zkClient.getChildren("/");
        for(String s : children_list){
            System.out.println(s);
        }
    }

    /***
     * 刪除節點
     */
    public void deleteNode(){
        String delete_node = "/node_zkClient";
        boolean e = zkClient.delete(delete_node);
        System.out.println("Delete node and children: " + e);
        boolean e2 = zkClient.deleteRecursive(delete_node);
        System.out.println("Delete node and children: " + e2);
    }

    /***
     * 判斷節點是否存在
     */
    public void existNode(){
        boolean exits = zkClient.exists(_NODE_);
        System.out.println("exits = " + exits);
    }

    /***
     * 订阅子节点列表发生变化
     * @throws InterruptedException
     */
    public void subscribeChildren() throws InterruptedException {
        List<String> results = zkClient.subscribeChildChanges(_NODE_,new ZkChildListener());
        //接下去往/node_zkClient下加入子节点后触发ZkChildListener事件
        Thread.sleep(Integer.MAX_VALUE);
    }


    /***
     * 数据变化监听器
     */
    public void subscribeData() throws InterruptedException {
        zkClient.subscribeDataChanges(_NODE_,new ZkDataListener());
        Thread.sleep(Integer.MAX_VALUE);
    }

    /***
     * 子节点列表发生变化_監聽器
     */
    static class ZkChildListener implements IZkChildListener{
        @Override
        public void handleChildChange(String s, List<String> list) throws Exception {
            System.out.println("Parent path: " + s);
            System.out.println("Current children: " + list.toString());
        }
    }


    /***
     * 订阅数据变化_监听器
     */
    static class ZkDataListener implements IZkDataListener{
        @Override
        public void handleDataChange(String s, Object o) throws Exception {
            System.out.println("Path Data Changed: " + s);
            System.out.println("Current Data: " + o.toString());
        }

        @Override
        public void handleDataDeleted(String s) throws Exception {
            System.out.println("Data Deleted: " + s);
        }
    }

}
