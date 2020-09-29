package xzy.guava;

import com.github.benmanes.caffeine.cache.*;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CaffeineTest {

    @Test
    public void test0(){
        Cache<String,String> cache = Caffeine.newBuilder()
                .expireAfterAccess(5, TimeUnit.SECONDS) //5秒没有读写自动删除
                .maximumSize(1024)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause removalCause) {
                        System.out.println(key + " " + value + " " + removalCause);
                    }
                }).build();
        cache.put("name" , "xuzy");
        System.out.println(cache.getIfPresent("name"));
        cache.invalidate("name");
        System.out.println(cache.getIfPresent("name"));
    }


    @Test
    public void test1(){
        Cache<String, String> cache = Caffeine.newBuilder().build();
        String name = cache.getIfPresent("name");
        System.out.println(name);
        name = cache.get("name", s -> "xuzy"); //如果获取不到就赋初始值
        System.out.println(name);
    }

    @Test
    public void test2(){
        LoadingCache<String,String> cache = Caffeine.newBuilder().build(new CacheLoader<String, String>() {
            @Nullable
            @Override
            public String load(@NonNull String key) {
                System.out.println("自动填充:" + key);
                return "initValue";
            }
        });
        String value = cache.getIfPresent("name");
        System.out.println(value);

        //get方法如果没值会自动初始化
        String value2 = cache.get("name");
        System.out.println(value2);
    }

    /***
     * 异步手动填充
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        AsyncCache<String,String> cache = Caffeine.newBuilder().buildAsync();
        //会返回一个 future对象， 调用future对象的get方法会一直卡住直到得到返回，和多线程的submit一样
        CompletableFuture<String> ageFuture = cache.get("name" ,name -> {
            System.out.println("name:" + name);
            return "initValue";
        });
        System.out.println(ageFuture.get());
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        AsyncLoadingCache<String, Integer> cache = Caffeine.newBuilder().buildAsync(name -> {
            System.out.println("name:" + name);
            return 18;
        });
        CompletableFuture<Integer> ageFuture = cache.get("张三");

        Integer age = ageFuture.get();
        System.out.println("age:" + age);
    }

    /***
     * 驱逐策略 - 基于容量大小
     */
    @Test
    public void test5() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder().maximumSize(10)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause cause) {
                        System.out.println(String.format("key %s was removed %s / %s", key, value, cause));
                    }
                }).build();
        for(int i = 0 ; i < 100; i++){
            cache.put("name" + i , String.valueOf(i));
        }
        Thread.currentThread().join();
    }

    /***
     * 驱逐策略 - 基于时间
     */
    @Test
    public void test6(){
        //写入后10秒过期,重新写入会刷新过期时间
        Cache<String, Integer> cache1 = Caffeine.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS).build();
        //写入或读取后10秒无任务操作会过期，读写都会刷新过期时间
        Cache<String, Integer> cache2 = Caffeine.newBuilder().expireAfterAccess(10, TimeUnit.SECONDS).build();
    }

}
