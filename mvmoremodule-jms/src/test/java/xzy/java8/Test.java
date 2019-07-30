package xzy.java8;

import com.google.common.collect.Lists;
import edu.emory.mathcs.backport.java.util.Collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2019-07-30-21:21
 */
public class Test {

    @org.junit.Test
    public void test1(){
        List<String> names = Lists.newArrayList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        names.sort((a,b) -> b.compareTo(a));


    }

    @org.junit.Test
    public void test2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("!!");
            }
        });
        new Thread(() -> System.out.println("!!!!"));
    }

    @org.junit.Test
    public void test3(){
        Converter<String,Integer> converter = from -> Integer.valueOf(from);
        Integer number = converter.convert("123");
        System.out.println("number :" + number);
    }


}
