package xzy.java8;

import org.junit.Test;

import java.util.function.Supplier;

public class SupplierTest {

    @Test
    public void test(){
        Supplier<Employee> supplier = () -> new Employee();
        Employee employee = supplier.get();
        System.out.println(employee);
    }

}
