package xzy.java8.lambda;

import org.junit.Test;

import java.util.function.Consumer;

public class ConsumerTest {
    @Test
    public void test(){
        Apple apple = new Apple();
        setFieldByConsumer(apple, new Consumer<Apple>() {
            @Override
            public void accept(Apple apple) {
                apple.setColor("red");
            }
        });
        System.out.println(apple);
    }


    public static Apple setFieldByConsumer(Apple apple,Consumer<Apple> consumer){
        consumer.accept(apple);
        return apple;
    }


}
