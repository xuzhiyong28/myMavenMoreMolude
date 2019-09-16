package xzy.java8.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Supplier;

/***
 * Supplier接口产生一个给定类型的结果
 * 作用：我们可以把耗资源运算放到get方法里，在程序里，我们传递的是Supplier对象，直到调用get方法时，运算才会执行。这就是所谓的惰性求值
 */
public class SupplierTest {
    private static List<Apple> appleList = Lists.newArrayList();

    static {
        appleList.add(new Apple("red", 100, 100));
        appleList.add(new Apple("red", 200, 250));
        appleList.add(new Apple("green", 100, 150));
        appleList.add(new Apple("green", 150, 300));
    }


    public static void supplier(Supplier<Apple> supplier){
        System.out.println("==========开始===========");
        Apple apple = supplier.get();
        System.out.println(apple);
        System.out.println("==========结束===========");
    }



    @Test
    public void test(){
        supplier(new Supplier<Apple>() {
            @Override
            public Apple get() {
                return appleList.get(1);
            }
        });
    }

}
