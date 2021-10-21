package xzy.caffeine;

import com.github.benmanes.caffeine.cache.*;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.sql.Time;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * https://segmentfault.com/a/1190000038665523 caffeine详解
 * https://www.cnblogs.com/crazymakercircle/p/14385641.html
 **/
public class CaffeineTest {
    final String KEY1 = "name";
    final String VALUE1 = "xuzhiyong";
    final String KEY2 = "name2";
    final String VALUE2 = "xuzhiyong2";
    final String DEF_VALUE = "def_value";

    @Test
    public void test1() {
        // 初始化缓存，设置了1分钟的写过期，100的缓存最大个数
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES) //写入后一分钟后过期
                //.expireAfterAccess(1, TimeUnit.MINUTES) // 访问后一分钟后过期
                .maximumSize(100)
                .build();

        // 手动填入值
        cache.put(KEY1, VALUE1);
        // 使用getIfPresent方法从缓存中获取值。如果缓存中不存指定的值，则方法将返回 null
        System.out.println(cache.getIfPresent(KEY1));
        // 也可以使用 get 方法获取值，该方法将一个参数为 key 的 Function 作为参数传入。如果缓存中不存在该 key 则该函数将用于提供默认值，该值在计算后插入缓存中：
        System.out.println(cache.get(KEY1, new Function<String, String>() {
            @Override
            public String apply(String s) {
                return DEF_VALUE;
            }
        }));

        cache.invalidate(KEY1); // 移除缓存
    }


    public String getByDB(String key) {
        if (key.equals(KEY1)) {
            return VALUE1;
        }
        if (key.equals(KEY2)) {
            return VALUE2;
        }
        return "value_by_db";
    }

    @Test
    public void test2() {
        // 初始化缓存，设置了1分钟的写过期，100的缓存最大个数
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES) //写入一分钟后过期
                .maximumSize(100)
                .build(new CacheLoader<String, String>() {
                    @Nullable
                    @Override
                    public String load(@NonNull String key) throws Exception {
                        return getByDB(key);
                    }
                });
        System.out.println(cache.get(KEY1)); //如果获取不到值就从getByDB方法中获取
        System.out.println(cache.get(KEY2));
        Map<String, String> all = cache.getAll(Arrays.asList(new String[]{KEY1, KEY2}));
        System.out.println(all);
    }

    /***
     * 异步加载机制
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        AsyncCache<String, String> asyncCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)   //写入一分钟后过期
                .maximumSize(100)
                .executor(Executors.newSingleThreadExecutor())
                .buildAsync();

        CompletableFuture<String> future = asyncCache.get(KEY1, new Function<String, String>() {
            @Override
            public String apply(String key) {
                // 执行所在的线程不在是main，而是ForkJoinPool线程池提供的线程
                System.out.println("当前所在线程：" + Thread.currentThread().getName());
                return getByDB(key);
            }
        });
        System.out.println(future.get());
    }

    /***
     * caffeine淘汰策略之基于时间
     * @throws InterruptedException
     */
    @Test
    public void test4() throws InterruptedException {
        // 自定义过期时间策略
        Cache<String, String> cache = Caffeine.newBuilder().expireAfter(new Expiry<String, String>() {
            // 创建1秒后过期，可以看到这里必须要用纳秒
            @Override
            public long expireAfterCreate(@NonNull String s, @NonNull String s2, long l) {
                return TimeUnit.SECONDS.toNanos(1);
            }

            // 更新2秒后过期，可以看到这里必须要用纳秒
            @Override
            public long expireAfterUpdate(@NonNull String s, @NonNull String s2, long l, @NonNegative long l1) {
                return TimeUnit.SECONDS.toNanos(2);
            }

            // 读3秒后过期，可以看到这里必须要用纳秒
            @Override
            public long expireAfterRead(@NonNull String s, @NonNull String s2, long l, @NonNegative long l1) {
                return TimeUnit.SECONDS.toNanos(3);
            }
        }).scheduler(Scheduler.systemScheduler()).build();

        cache.put(KEY1, VALUE1);
        System.out.println(cache.getIfPresent(KEY1));
        Thread.sleep(6000);
        System.out.println(cache.getIfPresent(KEY1));
    }

    /***
     * caffeine淘汰策略之基于大小
     * @throws InterruptedException
     * 我这边设置了最大缓存个数为1，当put进二个数据时则第一个就被淘汰了，此时缓存内个数只剩1个。
     * 之所以在demo中需要休眠一秒，是因为淘汰数据是一个异步的过程，休眠一秒等异步的回收结束。
     */
    @Test
    public void test5() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder().maximumSize(1).build();
        cache.put(KEY1, VALUE1);
        System.out.println(cache.estimatedSize()); //打印缓存个数
        System.out.println(cache.getIfPresent(KEY1));

        cache.put(KEY2, VALUE2);
        System.out.println(cache.estimatedSize()); //打印缓存个数
        System.out.println(cache.getIfPresent(KEY2));

        TimeUnit.SECONDS.sleep(1); // 淘汰是一个异步过程，所以要等待一下
        System.out.println(cache.getIfPresent(KEY1));
    }

    /***
     * caffeine淘汰策略之基于权重
     * 权重的还不是很理解，具体用到再看
     */
    @Test
    public void test6() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                .maximumWeight(2)
                .weigher(new Weigher<String, String>() {
                    @Override
                    public @NonNegative int weigh(@NonNull String key, @NonNull String value) {
                        return 1;   // 这里1 表示每次put一次总权重就+1，如果超过maximumWeight就淘汰
                    }
                }).build();
        cache.put(KEY1, VALUE1);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());

        cache.put(KEY2, VALUE2);
        // 稍微休眠一秒
        Thread.sleep(1000);
        // 打印缓存个数，结果为1
        System.out.println(cache.estimatedSize());

    }


}
