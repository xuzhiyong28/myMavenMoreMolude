package xzy.java8;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/***
 * 比较每种排序
 */
public class SortTest {

    public List<Employee> initList() {
        List<Employee> list = Lists.newArrayList();
        for (int i = 0; i < 100000; i++) {
            list.add(new Employee(UUID.randomUUID().toString(), RandomUtils.nextInt(0, 100000)));
        }
        return list;
    }


    @Test
    public void test0() {
        List<Employee> list = initList();
        long start = System.currentTimeMillis();
        list.sort((o1, o2) -> o1.getSalary() > o2.getSalary() ? -1 : 1);
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
