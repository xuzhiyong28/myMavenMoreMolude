package xzy.leetCode.hash;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/***
 * 用treeMap实现的一致性hash， 包含虚拟节点
 */
public class TreeMapHashVirtualTest {
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111","192.168.0.3:111", "192.168.0.4:111"};
    //真实节点,真实节点将不保存在Hash环中
    private static List<String> realNodes = new LinkedList<>();
    //虚拟节点,Hash环
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();
    //每个真实节点对应的虚拟节点数
    private static final int VIRTUAL_NODES = 10;
    static {
        //添加真实节点
        for (int i = 0; i < servers.length; i++)
            realNodes.add(servers[i]);
        //添加虚拟节点
        for (String str : realNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                //给虚拟节点命名
                String virtualNodeName = str + "&&VN" + String.valueOf(i);
                //重写Hash算法后的虚拟节点的Hash值
                int hash = getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++)
            hash = (hash ^ str.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) hash = Math.abs(hash);
        return hash;
    }

    private static String getServer(String node) {
        int hash = getHash(node);
        String virtualNode;
        Integer i;
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        if (subMap.size() == 0) {
            i = virtualNodes.firstKey();
            virtualNode = virtualNodes.get(i);
        } else {
            i = subMap.firstKey();
            virtualNode = subMap.get(i);
        }
        //返回真实节点的IP,端口,而不是虚拟节点名称
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (int i = 0; i < nodes.length; i++)
            System.out.println("[" + nodes[i] + "]的hash值为" + getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
    }
}
