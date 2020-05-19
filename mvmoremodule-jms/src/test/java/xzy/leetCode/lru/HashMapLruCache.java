package xzy.leetCode.lru;

import com.google.common.collect.Maps;

import java.util.Map;

/***
 *  通过HashMap实现LRU cache
 *  原理 : 通过一个双向链表来维护HashMap的关系，每次获取map中的项时，就重置链表关系，将获取的放到最后，放在最后面就是就是代表最近使用
 *         当数量超过阀值时，取链表前面的数据删除
 */
public class HashMapLruCache<V> {


    public static void main(String args[]){
        HashMapLruCache<String> lruCache = new HashMapLruCache<>(5);
        lruCache.set(1,"1");
        lruCache.set(2,"2");
        lruCache.set(3,"3");
        lruCache.set(4,"4");
        lruCache.set(5,"5");
        lruCache.set(6,"6");
        lruCache.set(7,"7");
        lruCache.set(8,"8");
        lruCache.printMap();

    }


    Map<Integer, Node> map = Maps.newHashMap();
    Node head;
    Node tail;
    int cap;
    public HashMapLruCache(int capacity) {
        cap = capacity;
        head = new Node(null, null);
        tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
    }

    public V get(int key){
        Node n = map.get(key);
        if(n != null){
            //后面三步也是先将其剔除出链表然后在添加到链表尾部
            n.pre.next = n.next;
            n.next.pre = n.pre;
            appendTail(n);
            return (V) n.value;
        }
        return null;
    }

    public void set(int key, V value) {
        Node n = map.get(key);
        if (n != null) {
            n.value = value;
            map.put(key, n);
            //后面三步将n从链表中剔除，然后在加入到链表尾部
            n.pre.next = n.next;
            n.next.pre = n.pre;
            appendTail(n);
            return;
        }

        //当链表满了以后，需要剔除最少使用的，也就是链头的数据
        if (map.size() == cap) {
            Node tmp = head.next;
            head.next = head.next.next;
            head.next.pre = head;
            map.remove(tmp.key);
        }

        n = new Node(key, value);
        appendTail(n);
        map.put(key, n);
    }


    /***
     * 插入到链表中形成如下方式
     * head <--> n <--> tail
     * @param n
     */
    private void appendTail(Node n) {
        n.next = tail;
        n.pre = tail.pre;
        tail.pre.next = n;
        tail.pre = n;
    }

    private void printMap(){
        for(Map.Entry<Integer, Node> entry : map.entrySet()){
            System.out.println(String.format("key = %s , value = %s", entry.getKey() , entry.getValue().value));
        }
    }


    class Node<V> {
        Node pre; // 前一个节点
        Node next; // 后一个节点
        Integer key;
        V value;

        Node(Integer k, V v) {
            key = k;
            value = v;
        }
    }

}
