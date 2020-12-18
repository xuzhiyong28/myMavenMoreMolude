package xzy.leetCode.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/***
 * LinkedHashMap 就是通过 HashMap+双向链表实现
 * 但是这个双向链表主要是为了实现数据的有序性，所以如果要做LRU需要重写
 * LRU : 最近最久未使用策略，优先淘汰最久未使用的数据，也就是上次被访问时间距离现在最久的数据。该策略可以保证内存中的数据都是热点数据，也就是经常被访问的数据，从而保证缓存命中率。
 * LinkedHashMap中本身就实现了一个方法removeEldestEntry用于判断是否需要移除最不常读取的数，方法默认是直接返回false，不会移除元素，所以需要【重写该方法】，即当缓存满后就移除最不常用的数。
 */
public class LinkedHashMapLruCache<V> {

    public static void main(String args[]){
        LinkedHashMapLruCache<String> lruCache = new LinkedHashMapLruCache<>(5);
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

    private int capacity;
    private Map<Integer,V> cache;

    public LinkedHashMapLruCache(int capacity){
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer,V>(capacity, 0.75f, true){
            // 定义put后的移除规则，大于容量就删除eldest
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public V get(int key){
        if(cache.containsKey(key)){
            return cache.get(key);
        }else {
            return null;
        }
    }

    public void set(int key , V value){
        cache.put(key, value);
    }

    private void printMap(){
        for(Map.Entry<Integer, V> entry : cache.entrySet()){
            System.out.println(String.format("key = %s , value = %s", entry.getKey() , entry.getValue()));
        }
    }


}
