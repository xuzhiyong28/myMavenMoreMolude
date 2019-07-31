package xzy.java8;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;

/***
 * 实现一个”一元函数“，即传入一个值经过函数的计算返回另一个值。
 */
public class FunctionTest {
    List<Employee> employeeList = Lists.newArrayList();
    @Before
    public void before(){
        String[] prefix = {"B", "A"};
        for (int i = 1; i <= 10; i++){
            employeeList.add(new Employee(prefix[i % 2] + i, i * 1000));
        }
    }

    @Test
    public void test1(){
        //int[] expenses = ListToArray(employeeList, employee -> Double.valueOf(employee.getSalary() * 1.2).intValue());
        int[] expenses = ListToArray(employeeList, new EmployeeToExpenses());
        System.out.println(expenses);
    }


    @Test
    public void test2(){
        Employee e = new Employee("xuzy",1000);
        Function<Employee,Integer> f1 = employee ->Double.valueOf(employee.getSalary() * 1.2).intValue();
        Function<Integer,String> f2 = aDouble -> String.valueOf(aDouble) + "元";
        String result = f1.andThen(f2).apply(e);
        Assert.assertEquals(result,"1200元");
    }


    @Test
    public void test3(){
        Function<String,String> f1 =  s -> (s + " Jack");
        Function<String,String> f2 = s -> (s + " Ke");
        String result = f1.compose(f2).apply("xuzy");
        Assert.assertEquals("xuzy Ke Jack",result);
    }




    private static int[] ListToArray(List<Employee> employeeList,Function<Employee,Integer> function){
        int[] ints = new int[employeeList.size()];
        for(int i = 0 ; i < ints.length ; i++){
            ints[i] = function.apply(employeeList.get(i));
        }
        return ints;
    }

    static class EmployeeToExpenses implements Function<Employee,Integer>{
        @Override
        public Integer apply(Employee employee) {
            return Double.valueOf(employee.getSalary() * 1.2).intValue();
        }
    }

    static class EmployeeToExpenses2 implements Function<Double,Double>{
        @Override
        public Double apply(Double aDouble) {
            return aDouble * 100;
        }
    }
}
