package xzy.guava;

import com.google.common.collect.Lists;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.List;

/***
 * guava 布隆过滤器算法
 */
public class BloomTest {
    @Test
    public void test1() {
        //一个是预计存放多少数据，一个是可以接受的误报率
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 50000000, 0.01);
        List<Integer> list = Lists.newArrayList();
        for (int i = 0; i < 50000000; i++) {
            filter.put(i);
            list.add(1);
        }
        long star = System.currentTimeMillis();
        Assert.assertTrue(filter.mightContain(1));
        Assert.assertTrue(filter.mightContain(2));
        Assert.assertTrue(filter.mightContain(3));
        Assert.assertFalse(filter.mightContain(50000000));
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - star));

        long star2 = System.currentTimeMillis();
        list.contains(1);
        list.contains(2);
        list.contains(3);
        list.contains(50000000);
        long end2 = System.currentTimeMillis();
        System.out.println("执行时间：" + (end2 - star2));



    }

    @Test
    public void test2(){
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b.put("121");
        b.put("122");
        b.put("123");
        System.out.println(b.mightContain("12321"));
        BloomFilter<String> b1 = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b1.put("aba");
        b1.put("abb");
        b1.put("abc");
        b1.putAll(b);
        System.out.println(b1.mightContain("123"));
    }

    @Test
    public void test3(){
        BloomFilter<String> b = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 1000, 0.000001);
        b.put("xuzhiyong");
        System.out.println(b);
    }


}
