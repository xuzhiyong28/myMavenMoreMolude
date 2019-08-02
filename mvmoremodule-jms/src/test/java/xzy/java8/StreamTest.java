package xzy.java8;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * @author xuzhiyong
 * @createDate 2019-07-31-22:15
 */
public class StreamTest {

    List<String> stringCollection = Lists.newArrayList();
    List<Integer> numberLists = Lists.newArrayList();
    List<Employee> employeeList = Lists.newArrayList();
    @Before
    public void before(){
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        numberLists.add(1);
        numberLists.add(2);
        numberLists.add(3);
        numberLists.add(4);
        numberLists.add(5);
        numberLists.add(6);
        String[] prefix = {"B", "A"};
        for (int i = 1; i <= 10; i++){
            employeeList.add(new Employee(prefix[i % 2] + i, i * 1000));
        }
    }


    @Test
    public void test(){
        //filter 筛选
        stringCollection.stream().filter(s -> s.startsWith("a")).forEach(s -> System.out.println(s));
    }

    @Test
    public void test2(){
        //sorted 排序
        stringCollection.stream().sorted().forEach(s -> System.out.println(s));
    }


    @Test
    public void test3(){
        int sum = employeeList.stream()
                .filter(employee -> employee.getName().startsWith("A"))
                .mapToInt(employee -> employee.getSalary())
                .sum();
        System.out.println(sum);
    }

    @Test
    public void test4(){
        Optional<Integer> sum = numberLists.stream().reduce((integer, integer2) -> integer + integer2);
        if(sum.isPresent()){
            System.out.println("list的总和为:" + sum.get());
        }
        int sum2 = numberLists.stream().reduce(0,(integer, integer2) -> integer + integer2);
        Assert.assertEquals(sum2,21);

        Optional<Integer> product = numberLists.stream().reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer * integer2;
            }
        });
        if(product.isPresent()){
            System.out.println("list的积为:" + product.get());
        }
    }


    @Test
    public void test5(){
        Integer product = numberLists.stream().reduce(1,(integer, integer2) -> {
           if(integer2 % 2 == 0 ) return integer * integer2; else return integer;
        });
        System.out.println("list的偶数的积为:" + product);
    }


}
