package xzy.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        List<Dish> list1 = menu.stream().filter(new Predicate<Dish>() {
            @Override
            public boolean test(Dish dish) {
                return dish.getCalories() > 300;
            }
        }).collect(Collectors.toList());
        System.out.println(list1);

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

        List<String> list1 = menu
                .stream()
                .map(dish -> dish.getName())
                .collect(Collectors.toList());
        System.out.println(list1);


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

    @Test
    public void test4(){
        //可以链式调用
        menu.stream().forEach(new Consumer<Dish>(){
            @Override
            public void accept(Dish o) {
                System.out.println(o.getName());
            }
        }.andThen(new Consumer<Dish>() {
            @Override
            public void accept(Dish dish) {
                System.out.println("!!!");
            }
        }));

        menu.stream().forEach(o -> System.out.println(o.getName()));

    }


    @Test
    public void testDistinct(){
        // distinct 的方法，它会返回一个元素各异（根据流所生成元素的hashCode 和 equals 方法实现）的流
        List<Integer> numbers = Lists.newArrayList(1,2,1,3,3,2,4);
        numbers
                .stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void testSkip(){
        //跳过前两个元素 跟 limit互补
        List<Dish> dishes = menu.stream().skip(2).collect(Collectors.toList());
        System.out.println(dishes);
    }


    @Test
    public void testDemo1(){
        //计算返回一串句子里面所有的单词，单词不重复
        // flatmap 方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流
        List<String> wordList = Lists.newArrayList("Hello", "World", "xuzy");
        List<String> uniqueCharacters = wordList.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream) //这个有点难理解。下次再看
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCharacters);
    }


    @Test
    public void testAnyMatch(){
        //流中是否有一个元素能匹配给定的谓词 -- 菜单中是否有包含至少一个素食
        if(menu.stream().anyMatch(dish -> dish.isVegetarian())){
            System.out.println("包含素食");
        }

        boolean isHealthy = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);
        System.out.println(isHealthy);

        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(dish.isPresent());
    }

    @Test
    public void testFindFirst(){
        List<Integer> someNumbers = Lists.newArrayList(1,2,3,4,5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree.get());
    }

    @Test
    public void testReduce(){
        //求总卡路里
        Optional<Integer> calories = menu.stream().map(new Function<Dish, Integer>() {
            @Override
            public Integer apply(Dish dish) {
                return dish.getCalories();
            }
        }).reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        System.out.println("calories = " + calories.get());


        Optional<Integer> calories2 = menu.stream()
                .map(Dish::getCalories)
                .reduce((integer, integer2) -> integer + integer2);
        System.out.println("calories = " + calories2.get());


        //求最大卡路里
        Optional<Integer> maxCalories = menu.stream().map(new Function<Dish, Integer>() {
            @Override
            public Integer apply(Dish dish) {
                return dish.getCalories();
            }
        }).reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer >= integer2 ? integer : integer2;
            }
        });
        System.out.println("calories = " + maxCalories.get());

        Optional<Integer> maxCalories2 = menu.stream()
                .map(Dish::getCalories)
                .reduce((integer, integer2) -> Math.max(integer,integer2));
        System.out.println("calories = " + maxCalories2.get());
    }

    @Test
    public void test6(){
        //// IntStream 、 DoubleStream 和LongStream ，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本
        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println(calories);
    }

    @Test
    public void test7(){
        //显示的创建一个流
        Stream<String> stringStream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stringStream.map(String::toUpperCase).forEach(System.out::println);

        //创建空流
        Stream<String> stringStream1 = Stream.empty();


        //数组创建流
        List<Integer> list = Arrays.stream(new Integer[]{2,3,4,5,6,7,8}).filter(integer -> integer > 5).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test8(){
        //获取最大最小卡路里的食物
        Optional<Dish> mostCalorieDish = menu.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        Optional<Dish> minCalorieDish = menu.stream()
                .collect(Collectors.minBy(Comparator.comparing(Dish::getCalories)));
        System.out.println(minCalorieDish.get().getName());


        //汇总
        int totalCalories = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        //连接字符串
        String menuName = menu.stream().map(Dish::getName).collect(Collectors.joining(";"));
        System.out.println(menuName);
    }


}
