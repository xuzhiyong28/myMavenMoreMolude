package xzy.guava;/**
 * Created by Administrator on 2018-10-14.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2018-10-14-17:07
 */
public class ListsTest {

    public <T extends Object > void printList(T t){
        System.out.println(JSON.toJSON(t).toString());
    }

    @Test
    public void test1() {
        List<Integer> list1 = Lists.newArrayList();
        List<Integer> list2 = Lists.newArrayList(1, 2, 3, 4, 5, 6);
        List<Integer> list3 = Lists.newArrayList(list2);
        List<Integer> list4 = Lists.newArrayList(new Integer[]{1, 2, 3, 4, 5, 6});
    }

    @Test
    public void test2() {

        //使用条件：你确定你的容器会装多少个，不确定就用一般形式的
        //说明：这个容器超过10个还是会自动扩容的。不用担心容量不够用。默认是分配一个容量为10的数组，不够将扩容
        //执行数组数据迁移操作：新建新数组，复制就数组数据到新数组（包括开辟新空间，copy数据等等，耗时，耗性能）
        //对下数字10的说明：若直接new一个list的话，默认大小是10的数组，下面的方式则是 5L + x + x/10 = 16L(x = 10)，在17的时候扩容
        //整个来说的优点有：节约内存，节约时间，节约性能。代码质量提高。
        List<String> list1 = Lists.newArrayListWithExpectedSize(10);
        System.out.println(list1.size());
        //这个方法就是直接返回一个10的数组。
        List<String> list2 = Lists.newArrayListWithCapacity(10);
        System.out.println(list2.size());
    }

    @Test
    public void test3() {
        //这个方法将list根据后面的数字进行分割成多个list
        List<String> list = Lists.newArrayList(new String[]{"1", "2", "3", "4", "5", "6","7"});
        List<List<String>> listTemp = Lists.partition(list, 2);
        System.out.println(JSON.toJSON(listTemp).toString()); //[["1","2"],["3","4"],["5","6"],["7"]]
        //尝试改变分割后的list,发现原来的list发生了变化。所以这里会对原来的数据list造成改变
        listTemp.get(0).add("0");
        System.out.println(JSON.toJSON(list).toString());
    }

    @Test
    public void test4(){
        List<String> list = Lists.newArrayList(new String[]{"1", "2", "3", "4", "5", "6","7"});
        printList(Lists.reverse(list));
    }

    @Test
    public void test5(){
        Map<String,String> map = Maps.newHashMap();
        map.put("1","test1");
        map.put("2","test2");
        Map<String,String> map2 = Maps.newHashMap();
        map2.put("1","test3");
        map2.put("4","test2");
        List<Map<String,String>> list = Lists.newArrayList();
        list.add(map);
        list.add(map2);
        //有点像javasrcipt中的map方法，把对象list中的某个属性取出来  返回一个新的list
        List<String> list2 = Lists.transform(list, new Function<Map<String, String>, String>() {
            @Override
            public String apply(Map<String, String> stringStringMap) {
                return stringStringMap.get("1");
            }
        });
        printList(list2);//["test1","test3"]

    }

}
