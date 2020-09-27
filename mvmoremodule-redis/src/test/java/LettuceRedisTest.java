import com.google.common.collect.Lists;
import io.lettuce.core.*;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/***
 * https://www.cnblogs.com/throwable/p/11601538.html
 */
public class LettuceRedisTest {


    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;
    private RedisCommands<String, String> syncCommands;
    private RedisAsyncCommands<String, String> asyncCommands;
    private RedisReactiveCommands<String,String> redisReactiveCommands;

    @Before
    public void before() {
        RedisURI redisURI = RedisURI.Builder.redis("localhost", 6379).withTimeout(Duration.ofSeconds(30)).build();
        redisClient = RedisClient.create(redisURI);
        connection = redisClient.connect();
        syncCommands = connection.sync();
        asyncCommands = connection.async();
        redisReactiveCommands = connection.reactive();
    }

    @After
    public void after() {
        connection.close();
        redisClient.shutdown();
    }


    @Test
    public void syncGetAndSet() {
        String result = syncCommands.set("name", "xuzy");
        Assert.assertEquals(result, "OK");
        String name = syncCommands.get("name");
        Assert.assertEquals(name, "xuzy");
    }

    @Test
    public void asyncGetAndSet() throws InterruptedException, ExecutionException, TimeoutException {
        RedisFuture<String> future = asyncCommands.get("name");
        future.get(10, TimeUnit.SECONDS);
        //设置异步的监听器
        future.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s === " + s);
            }
        });
    }


    @Test
    public void asyncGetAndSet2() throws InterruptedException, ExecutionException, TimeoutException {
        RedisFuture<String> future = asyncCommands.get("name");
        future.get(10, TimeUnit.SECONDS);
        future.thenAcceptAsync(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s :" + s);
            }
        });
    }

    @Test
    public void asyncGetAndSet3() {
        List<RedisFuture<String>> futures = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            futures.add(asyncCommands.set("key-" + i, "value-" + i));
        }
        boolean isSuccess = LettuceFutures.awaitAll(1, TimeUnit.MINUTES, futures.toArray(new RedisFuture[futures.size()]));
        Assert.assertEquals(isSuccess,true);
    }


    @Test
    public void completableFutrueTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        Assert.assertEquals(false, future.isDone());
        future.complete("my value");
        Assert.assertEquals(true, future.isDone());
        Assert.assertEquals("my value", future.get());

        String result = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello";
            }
        }).thenApply(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + " world";
            }
        }).join();
        Assert.assertEquals("hello world", result);
    }

    @Test
    public void completableFutrueTest2() {
        CompletableFuture cf = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                Assert.assertTrue(Thread.currentThread().isDaemon());
                randomSleep();
            }
        });
        Assert.assertFalse(cf.isDone());
        sleepEnough();
        Assert.assertTrue(cf.isDone());
    }



    @Test
    public void test1(){
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        String result = syncCommands.set("name" , "throwable" , setArgs);
        System.out.println(result);
    }


    @Test
    public void ping(){
        String ping = syncCommands.ping();
        System.out.println(ping);
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        RedisFuture<String> redisFuture = asyncCommands.set("name", "throwable", setArgs);
        redisFuture.thenAccept(value -> {
            System.out.println("返回 : " + value);
        });
        redisFuture.get();
    }

    @Test
    public void test3(){
        redisReactiveCommands.sadd("food", "bread", "meat", "fish").block();
        Flux<String> foodSetFlux = redisReactiveCommands.smembers("food");
        foodSetFlux.subscribe(System.out::println);
        redisReactiveCommands.srem("food", "bread");
        Flux<String> foodSetFlux2 = redisReactiveCommands.smembers("food");
        foodSetFlux2.subscribe(System.out::println);
    }

    @Test
    public void testBatch(){
        syncCommands.multi();
        syncCommands.setex("name-1", 2, "throwable");
        syncCommands.setex("name-2", 2, "doge");
        TransactionResult exec = syncCommands.exec();
        for(Object o : exec){
            System.out.println(o);
        }
    }

    /***
     * 反应式方式ping-pong
     */
    @Test
    public void pingReactive(){
        Mono<String> pingMono = redisReactiveCommands.ping();
        pingMono.subscribe(System.out::println);
    }


    /***
     * 使用连接池的实例
     */
    @Test
    public void lettuceUsePool() throws Exception {
        RedisURI redisUri = RedisURI.builder()
                .withHost("localhost")
                .withPort(6379)
                .withTimeout(Duration.of(10, ChronoUnit.SECONDS))
                .build();
        RedisClient redisClient = RedisClient.create(redisUri);
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(100);
        poolConfig.setMaxTotal(100);
        poolConfig.setMaxWaitMillis(1000);
        GenericObjectPool<StatefulRedisConnection<String, String>> pool = ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, poolConfig);
        StatefulRedisConnection<String, String> connection = pool.borrowObject();
        RedisCommands<String, String> command = connection.sync();
        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        command.set("name", "throwable", setArgs);
        String n = command.get("name");
        pool.close();
        redisClient.shutdown();
    }




    public void randomSleep() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepEnough() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
