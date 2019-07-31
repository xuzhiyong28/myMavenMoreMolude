package xzy.java8;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;

/**
 * @author xuzhiyong
 * @createDate 2019-07-31-21:52
 */
public class ConsumerTest {
    List<Employee> employeeList = Lists.newArrayList();
    @Before
    public void before(){
        String[] prefix = {"B", "A"};
        for (int i = 1; i <= 10; i++){
            employeeList.add(new Employee(prefix[i % 2] + i, i * 1000));
        }
    }

    @Test
    public void test(){
        //employeeList.forEach(new SalaryConsumer());
        employeeList.forEach(employee -> {
            if (employee.getName().startsWith("A")) {
                System.out.println(employee.getName() + " salary is " + employee.getSalary());
            }
        });
    }

    @Test
    public void test2(){
        SalaryConsumer salaryConsumer = new SalaryConsumer();
        Employee employee = new Employee("A",1000);
        salaryConsumer.accept(employee);
    }


    @Test
    public void test3(){
        employeeList.forEach(employee -> {
            employee.setName("zzz");
        });
        System.out.println(employeeList);
    }

    static class SalaryConsumer implements Consumer<Employee>{

        @Override
        public void accept(Employee employee) {
            if (employee.getName().startsWith("A")) {
                System.out.println(employee.getName() + " salary is " + employee.getSalary());
            }
        }
    }

}
