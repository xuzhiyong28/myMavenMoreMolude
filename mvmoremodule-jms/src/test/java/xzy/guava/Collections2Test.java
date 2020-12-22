package xzy.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import edu.emory.mathcs.backport.java.util.Collections;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class Collections2Test {
    @Test
    public void test1() {
        //对list进行过滤,过滤掉不是2的数值
        List<String> list = Lists.newArrayList("1", "2", "3", "4");
        Collection<String> filterList = Collections2.filter(list, new Predicate<String>() {
            @Override
            public boolean apply(String s) {
                return s.equals("2");
            }
        });
        System.out.println(filterList);
    }

    @Test
    public void test2(){
        List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
        Collection<Integer> nameLength = Collections2.transform(names, new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });
        System.out.println(nameLength);
    }

}
