package xzy.java8.newFuture;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author xuzhiyong
 * @createDate 2019-09-04-22:21
 */
public class Test1 {
    public static List<Shop> shops = Lists.newArrayList(
            new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll")
    );


    @Test
    public void test() {
        String product = "myPhone27S";
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(new Function<Shop, CompletableFuture<String>>() {
                    @Override
                    public CompletableFuture<String> apply(Shop shop) {
                        return CompletableFuture.supplyAsync(new Supplier<String>() {
                            @Override
                            public String get() {
                                return String.format("%s price is %.2f", shop.getName(), shop.getPrice(product));
                            }
                        });
                    }
                }).collect(Collectors.toList());


        List<String> result = priceFutures.stream()
                .map(new Function<CompletableFuture<String>, String>() {
                    @Override
                    public String apply(CompletableFuture<String> stringCompletableFuture) {
                        return stringCompletableFuture.join();
                    }
                }).collect(Collectors.toList());

        System.out.println(result);

    }


    /****
     * 通过产品找到价格
     */
    @Test
    public void test2() {

        //采用无限流生成100000个对象作为测试数据
        List<Shop> testShopList = Stream.generate(new Supplier<Shop>() {
            @Override
            public Shop get() {
                return new Shop(getRandomString(10));
            }
        }).limit(100).collect(Collectors.toList());



        String product = "myPhones";
        //1.采用流字节去处理,顺序执行的
        StopWatch watch = new StopWatch();
        watch.start();
        testShopList.stream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());

        System.out.println("顺序执行耗时:" + watch.getTime());

        watch.reset();
        watch.start();

        //2.采用并发流的方式
        testShopList.parallelStream()
                .map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)));
        System.out.println("并发流执行耗时:" + watch.getTime());

        watch.reset();
        watch.start();
        //3.采用CompletableFuture,用这个的好处是可以自己指定线程池的数量，不像并发流是通用的线程池
        //如果你进行的是计算密集型的操作，并且没有I/O，那么推荐使用 Stream 接口
        //如果你并行的工作单元还涉及等待I/O的操作（包括网络连接等待），那么使用CompletableFuture 灵活性更好
        List<CompletableFuture<String>> priceFutures = testShopList.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))))
                .collect(Collectors.toList());

        priceFutures.stream().map(stringCompletableFuture -> stringCompletableFuture.join()).collect(Collectors.toList());
        System.out.println("CompletableFuture执行耗时:" + watch.getTime());
    }


    /***
     * 随机生成字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
