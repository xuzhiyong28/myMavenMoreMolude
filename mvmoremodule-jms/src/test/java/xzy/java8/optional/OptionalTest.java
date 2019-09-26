package xzy.java8.optional;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {

    @Test
    public void test(){
        Optional<Car> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    @Test
    public void test2(){
        Insurance insurance = new Insurance("xuzy");
        Optional<Insurance> opt = Optional.ofNullable(insurance);
        Assert.assertTrue(opt.isPresent()); //判断对象是否为空
        //检查是否有值的另一个选择是 ifPresent() 方法。该方法除了执行检查，还接受一个Consumer(消费者) 参数，如果对象不是空的，就对执行Consumer
        //不为 null 的时候才会执行Consumer
        opt.ifPresent(new Consumer<Insurance>(){
            @Override
            public void accept(Insurance insurance) {
            }
        });
    }
}
