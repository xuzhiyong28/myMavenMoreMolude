package xzy.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author xuzhiyong
 * @createDate 2019-09-04-21:03
 */
public class Java8StreamTest2 {

    private static enum CaloricLevel { DIET , NORMAL , FAT};

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
        //分组 -把菜单中的菜按照类型进行分类有肉的放一组，有鱼的放一组，其他的都放另一组
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(Collectors.groupingBy(new Function<Dish, Dish.Type>() {
                    @Override
                    public Dish.Type apply(Dish dish) {
                        return dish.getType();
                    }
         }));
        System.out.println(dishesByType);

        Map<Dish.Type, List<Dish>> dishesByType2 = menu.stream()
                .collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishesByType2);

    }

    @Test
    public void test2(){
        //分组 -- 热量不到400卡路里的菜划分为“低热量”（diet），热量400到700卡路里的菜划为“普通”（normal），高于700卡路里的划为“高热量”（fat）
        Map<CaloricLevel,List<Dish>> caloricLevelListMap = menu.stream()
                .collect(Collectors.groupingBy(dish -> {
                    if(dish.getCalories() <= 400){
                        return CaloricLevel.DIET;
                    }
                    else if(dish.getCalories() >= 700){
                        return CaloricLevel.FAT;
                    }
                    else{
                        return CaloricLevel.NORMAL;
                    }
                }));
        System.out.println(caloricLevelListMap);
    }

    @Test
    public void test3(){
        //多级分组 先根据肉，素，其他分组，然后里面再根据卡路里分组
        Map<Dish.Type,Map<CaloricLevel,List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
                .collect(Collectors.groupingBy(new Function<Dish, Dish.Type>() {
                    @Override
                    public Dish.Type apply(Dish dish) {
                        return dish.getType();
                    }
                },Collectors.groupingBy(new Function<Dish, CaloricLevel>() {
                    @Override
                    public CaloricLevel apply(Dish dish) {
                        if(dish.getCalories() <= 400){
                            return CaloricLevel.DIET;
                        }
                        else if(dish.getCalories() >= 700){
                            return CaloricLevel.FAT;
                        }
                        else{
                            return CaloricLevel.NORMAL;
                        }
                    }
                })));
        System.out.println(dishesByTypeCaloricLevel); //好复杂
    }


    @Test
    public void testGenerate(){
        //无限流生成5个随机数然后打印出来
        Stream.generate(Math::random).limit(5).forEach(System.out::println);

    }

    /***
     * 并行流存在并发问题
     * 总结就是paralleStream里直接去修改变量是非线程安全的，但是采用collect和reduce操作就是满足线程安全的了
     */
    @Test
    public void parallelError(){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        IntStream.range(0, 10000).forEach(list1::add);
        IntStream.range(0, 10000).parallel().forEach(list2::add);
        System.out.println("串行执行的大小：" + list1.size());
        System.out.println("并行执行的大小：" + list2.size());
    }


    @Test
    public void testOther(){
        List<String> dateList = Lists.newArrayList("20180306","20180307","20180308","20180309","20180310","20180311","20180312","20180313","20180314","20180414");
        Map<String,List<String>> group = dateList.stream().collect(Collectors.groupingBy(new Function<String, String>() {
            @Override
            public String apply(String date) {
                return String.valueOf(Integer.valueOf(date) % 4);
            }
        }));
        System.out.println(group);
    }

}