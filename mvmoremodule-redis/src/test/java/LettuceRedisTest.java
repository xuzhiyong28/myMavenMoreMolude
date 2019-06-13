import com.google.common.collect.Lists;
import io.lettuce.core.LettuceFutures;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class LettuceRedisTest {


    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;
    private RedisCommands<String, String> syncCommands;
    private RedisAsyncCommands<String, String> asyncCommands;

    @Before
    public void before() {
        RedisURI redisURI = RedisURI.Builder.redis("localhost", 6379).withTimeout(Duration.ofSeconds(30)).build();
        redisClient = RedisClient.create(redisURI);
        connection = redisClient.connect();
        syncCommands = connection.sync();
        asyncCommands = connection.async();
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
        LettuceFutures.awaitAll(1, TimeUnit.MINUTES, futures.toArray(new RedisFuture[futures.size()]));
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
