package xzy.guava;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.List;

/***
 * 它和set最大的区别就是它可以对相同元素做一个计数的功能
 */
public class MultisetTest {

    public void printMuitiSet(Multiset<String> wordMultiset){
        System.out.println("=====================");
        for(Object key : wordMultiset.elementSet()){
            System.out.println(key + "--->" + wordMultiset.count(key));
        }
    }

    @Test
    public void test1(){
        String str = "张三 李四 李四 王五 王五 王五";
        Splitter splitter = Splitter.on(" ");
        List<String> list = splitter.splitToList(str);
        Multiset<String> wordMultiset = HashMultiset.create();
        wordMultiset.addAll(list);
        printMuitiSet(wordMultiset);

        wordMultiset.add("李四");
        wordMultiset.add("许高");
        printMuitiSet(wordMultiset);

        wordMultiset.removeAll(list);
        printMuitiSet(wordMultiset);
    }
}
