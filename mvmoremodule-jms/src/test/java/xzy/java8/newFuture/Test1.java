package xzy.java8.newFuture;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    public void test(){
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

}
