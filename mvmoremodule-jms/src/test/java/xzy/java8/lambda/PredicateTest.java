package xzy.java8.lambda;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/****
 * 函数式编程，传入一个函数，就可以判断是否正确
 */
public class PredicateTest {
    private static List<Apple> appleList = Lists.newArrayList();

    static {
        appleList.add(new Apple("red", 100, 100));
        appleList.add(new Apple("red", 200, 250));
        appleList.add(new Apple("green", 100, 150));
        appleList.add(new Apple("green", 150, 300));
    }

    public static List<Apple> getAppleListByPredicate(Predicate predicate) {
        List<Apple> resultList = Lists.newArrayList();
        for (Apple apple : appleList) {
            if (predicate.test(apple)) {
                resultList.add(apple);
            }
        }
        return resultList;
    }

    /***
     * stream + predicate结合
     * @param predicate
     * @return
     */
    public static List<Apple> getAppleListByPredicateAndStream(Predicate predicate) {
        return (List<Apple>) appleList.stream().filter(predicate).collect(Collectors.toList());
    }


    /***
     * stream + predicate + 泛型
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    public static <T> List<T> getAppleListByPredicateAndStreamT(List<T> list,Predicate<T> predicate){
        return list.stream().filter(predicate).collect(Collectors.toList());
    }



    @Test
    public void test1() {
        List<Apple> list0 = getAppleListByPredicate(new Predicate<Apple>() {
            @Override
            public boolean test(Apple o) {
                return o.getColor().equals("green");
            }
        });
        //过滤出红色苹果的数据
        List<Apple> list1 = getAppleListByPredicate((Predicate<Apple>) o -> o.getColor().equals("red"));
        System.out.println(list1);
        List<Apple> list2 = getAppleListByPredicate((Predicate<Apple>) o -> o.getWeight() > 100);
        System.out.println(list2);
    }


    @Test
    public void test2() {
        List<Apple> list0 = getAppleListByPredicate(new Predicate<Apple>() {
            @Override
            public boolean test(Apple o) {
                return o.getColor().equals("red");
            }
        }.and(new Predicate<Apple>() {
            @Override
            public boolean test(Apple o) {
                return o.getPrice() > 100;
            }
        }));
        System.out.println(list0);

        List<Apple> list1 = getAppleListByPredicate(((Predicate<Apple>) o -> o.getColor().equals("red")).and(o -> o.getPrice() > 100));
        System.out.println(list1);

    }

    @Test
    public void test3() {
        List<Apple> list0 = getAppleListByPredicate(new Predicate<Apple>() {
            @Override
            public boolean test(Apple o) {
                return o.getColor().equals("red");
            }
        }.or(new Predicate<Apple>() {
            @Override
            public boolean test(Apple o) {
                return o.getPrice() >= 250;
            }
        }));
        System.out.println(list0);

        List<Apple> list1 = getAppleListByPredicate(((Predicate<Apple>) o -> o.getColor().equals("red")).or(o -> o.getPrice() >= 250));
        System.out.println(list1);
    }


}
