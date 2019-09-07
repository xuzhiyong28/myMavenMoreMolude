package xzy.java8.newFuture;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author xuzhiyong
 * @createDate 2019-09-04-22:01
 */
public class Shop {

    private String name;

    public Shop(){

    }
    public Shop(String name){
        this.name = name;
    }

    public double getPrice(String produce) {
        return calculatePrice(produce);
    }

    private double calculatePrice(String product) {
        delay();
        return RandomUtils.nextDouble(1, 10) * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /***
     * 普通模式创建一个CompletableFuture
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try{
                //如果价格计算正常结束，完成 Future 操作并设置商品价格
                double price = calculatePrice(product);
                futurePrice.complete(price);
            }catch (Exception e){
                //否则就抛出导致失败的异常，完成这次 Future 操作
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }


    /***
     * 直接通过api去创建
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync2(String product){
        return CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                return calculatePrice(product);
            }
        });
    }




    public static void main(String[] args) throws InterruptedException {
        Shop shop = new Shop();
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");


        //执行更多操作
        TimeUnit.SECONDS.sleep(1);

        // 在计算商品价格的同时从 Future 对象中读取价格，如果价格未知，会发生阻塞
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
