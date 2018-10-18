package xzy.guava;/**
 * Created by Administrator on 2018-10-18.
 */

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2018-10-18-22:05
 * 其它  转 字符串
 */
public class JoinerTest {
    @Test
    public void test1(){
        //将List转成String
        List<String> list = Lists.newArrayList("1","2","3","4");
        Joiner joiner = Joiner.on(";");
        System.out.println(joiner.join(list));
    }

    @Test
    public void test2(){
        String str = Joiner.on(";").skipNulls().join(new String[]{"1","2","3","4",null,"6"});
        System.out.println(str);
        String str2 = Joiner.on(";").useForNull("空").join(new String[]{"1","2","3","4",null,"6"});
        System.out.println(str2);
    }

    @Test
    public void test3(){
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        Joiner.MapJoiner mapJoiner = Joiner.on(",").withKeyValueSeparator("=");
        System.out.println(mapJoiner.join(map));
    }
}
