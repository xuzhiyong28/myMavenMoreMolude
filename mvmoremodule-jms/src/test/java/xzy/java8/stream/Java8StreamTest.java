package xzy.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Java8StreamTest {
    private List<Dish> menu = Lists.newArrayList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    @Test
    public void test1(){
        List<Dish> list = menu
                .stream()
                .filter(dish -> dish.getCalories() > 300)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test2(){
        List<String> list = menu
                .stream()
                .map(new Function<Dish, String>() {
                    @Override
                    public String apply(Dish dish) {
                        return dish.getName();
                    }
                })
                .collect(Collectors.toList());
        System.out.println(list);

        List<String> list2 = menu
                .stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(list2);
    }

    @Test
    public void test3(){
        //只选择前三个 可以用来做分页
        List<Dish> list = menu.stream().limit(3).collect(Collectors.toList());
        System.out.println(list);
    }
}
