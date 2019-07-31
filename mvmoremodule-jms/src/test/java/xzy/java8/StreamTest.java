package xzy.java8;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author xuzhiyong
 * @createDate 2019-07-31-22:15
 */
public class StreamTest {

    List<String> stringCollection = Lists.newArrayList();
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

}
