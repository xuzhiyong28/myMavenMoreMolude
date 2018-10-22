package xzy.guava;

import com.google.common.cache.*;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheTest {
    @Test
    public void test1() {
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("word", "Hello Guava Cache");
        System.out.println(cache.getIfPresent("word"));
    }

    @Test
    public void test2() {
        //缓存设置属性
        //expireAfterWrite方法指定对象被写入到缓存后多久过期
        //expireAfterAccess指定对象多久没有被访问后过期
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(2) //最多两条缓存
                .expireAfterAccess(5, TimeUnit.SECONDS) //10秒后自动删除
                .build();
        cache.put("word", "Hello Guava Cache");
        System.out.println(cache.getIfPresent("word"));
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache.getIfPresent("word"));
    }

    @Test
    public void test3() {
        //可以通过weakKeys和weakValues方法指定Cache只保存对缓存记录key和value的弱引用。这样当没有其他强引用指向key和value时，key和value对象就会被垃圾回收器回收
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(2)
                .weakValues()
                .build();
        Object value = new Object();
        cache.put("key1", value);
        value = new Object();
        System.gc();
        System.out.println(cache.getIfPresent("key1"));
    }

    @Test
    public void test4() {
        //批量删除cache
        Cache<String, String> cache = CacheBuilder.newBuilder().build();
        cache.put("key1", "xxx");
        cache.put("key2", "zzz");
        cache.put("key3", "ccc");
        cache.put("key4", "vvv");
        List<String> list = Lists.newArrayList("key1", "key2");
        cache.invalidateAll(list);
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        System.out.println(cache.getIfPresent("key3"));
        System.out.println(cache.getIfPresent("key4"));
    }

    @Test
    public void test5() {
        //给缓存设置移除监听器，当有缓存被移除时触发监听
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).removalListener(new RemovalListener<Object, Object>() {
            @Override
            public void onRemoval(RemovalNotification<Object, Object> notification) {
                System.out.println("[" + notification.getKey() + ":" + notification.getValue() + "] is removed!");
            }
        }).build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.invalidate("key1");
    }

    @Test
    public void test6() throws InterruptedException {
        //Cache的get方法有两个参数，第一个参数是要从Cache中获取记录的key，第二个记录是一个Callable对象。当缓存中已经存在key对应的记录时，get方法直接返回key对应的记录。
        // 如果缓存中不包含key对应的记录，Guava会启动一个线程执行Callable对象中的call方法，call方法的返回值会作为key对应的值被存储到缓存中，并且被get方法返回
        final Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread1");
                try {
                    String value = cache.get("key", new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println("load1"); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "auto load by Callable";
                        }
                    });
                    System.out.println("thread1 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread2");
                try {
                    String value = cache.get("key", new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            System.out.println("load2"); //加载数据线程执行标志
                            Thread.sleep(1000); //模拟加载时间
                            return "auto load by Callable";
                        }
                    });
                    System.out.println("thread2 " + value);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        //从结果中可以看出，虽然是两个线程同时调用get方法，但只有一个get方法中的Callable会被执行(没有打印出load2)。
        // Guava可以保证当有多个线程同时访问Cache中的一个key时，如果key对应的记录不存在，Guava只会启动一个线程执行get方法中Callable参数对应的任务加载数据存到缓存。
        // 当加载完数据后，任何线程中的get方法都会获取到key对应的值。
    }

    @Test
    public void test7() {
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(3)
                .recordStats()//开启统计信息开关
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        cache.getIfPresent("key1");
        cache.getIfPresent("key2");
        cache.getIfPresent("key3");
        cache.getIfPresent("key4");
        cache.getIfPresent("key5");
        cache.getIfPresent("key6");
        System.out.println(cache.stats()); //获取统计信息
    }

    @Test
    public void test8() throws ExecutionException {
        //与构建Cache类型的对象类似，LoadingCache类型的对象也是通过CacheBuilder进行构建，不同的是，在调用CacheBuilder的build方法时，必须传递一个CacheLoader类型的参数，CacheLoader的load方法需要我们提供实现。
        // 当调用LoadingCache的get方法时，如果缓存不存在对应key的记录，则CacheLoader中的load方法会被自动调用从外存加载数据，load方法的返回值会作为key对应的value存储到LoadingCache中，并从get方法返回
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().build(new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        });
        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");
    }
}
