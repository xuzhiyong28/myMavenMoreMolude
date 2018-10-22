package xzy.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Test;

/***
 *  bimap的作用很清晰：它是一个一一映射，可以通过key得到value，也可以通过value得到key
 *  bimap和普通HashMap区别
 *  1.在Java集合类库中的Map，它的特点是存放的键（Key）是唯一的，而值（Value）可以不唯一，而bimap要求key和value都唯一，如果key不唯一则覆盖key，如果value不唯一则直接报错
 */
public class BimapTest {
    @Test
    public void test1(){
        BiMap<Integer,String> biMap = HashBiMap.create();
        biMap.put(1,"张三");
        biMap.put(2,"李四");
        biMap.put(3,"王五");
        biMap.put(4,"赵六");
        biMap.put(5,"李七");
        biMap.put(4,"小小");
        System.out.println(biMap.get(1));
        int key = biMap.inverse().get("张三");
        System.out.println("key=" + key);
    }
}
