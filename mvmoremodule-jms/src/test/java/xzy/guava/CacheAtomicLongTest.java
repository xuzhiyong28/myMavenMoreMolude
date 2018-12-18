package xzy.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/****
 * AtomicLong + GuavaCache做限流
 */
public class CacheAtomicLongTest {
    //如果counter.get(xxx)没有值，则会执行build里面的方法来初始化
    public static LoadingCache<Long, AtomicLong> counter = CacheBuilder.newBuilder().expireAfterWrite(2, TimeUnit.SECONDS).build(new CacheLoader<Long, AtomicLong>() {
        @Override
        public AtomicLong load(Long aLong) throws Exception {
            return new AtomicLong(0);
        }
    });

    public static void main(String[] args) throws ExecutionException {
        long limit = 1000;
        while(true){
            //一秒之内如果超过1000个调用则限流
            long currentSeconds = System.currentTimeMillis() / 1000;
            if(counter.get(currentSeconds).incrementAndGet() > limit){
                System.out.println("限流了:" + currentSeconds);
                continue;
            }
        }
    }

}
