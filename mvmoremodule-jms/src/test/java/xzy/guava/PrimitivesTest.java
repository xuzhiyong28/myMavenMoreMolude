package xzy.guava;

import com.alibaba.fastjson.JSONObject;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Primitives;
import org.junit.Test;

import java.util.Set;

public class PrimitivesTest {
    @Test
    public void test1() {
        String key = Ints.join(",", 1, 2, 3, 4, 5, 6, 7);
        System.out.println(key);
        int[] keys = Ints.concat(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 6, 7}); //合并多个数值数组
        System.out.println(keys.length);
    }

    @Test
    public void test2(){
        Set<Class<?>> set = Primitives.allPrimitiveTypes();
        System.out.println(set);
        System.out.println(Primitives.isWrapperType(Integer.class)); //判断是不是基础类型
    }
}
