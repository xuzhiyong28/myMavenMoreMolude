package xzy.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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
}