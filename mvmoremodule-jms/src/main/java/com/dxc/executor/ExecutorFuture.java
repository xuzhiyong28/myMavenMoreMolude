package com.dxc.executor;/**
 * Created by Administrator on 2019-01-16.
 */
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import java.util.List;
import java.util.concurrent.*;
public class ExecutorFuture {
    private static final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static final List<Future<String>> futureList = Lists.newArrayList();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for(int i = 0 ; i < 100 ; i++){
            Mycallable mycallable = new Mycallable();
            futureList.add(executorService.submit(mycallable));
        }
        executorService.shutdown();
        for(Future<String> future : futureList){
            String value = future.get(); //会阻塞获取每个线程返回的值
            System.out.println(value);
        }
    }
    public static class Mycallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            TimeUnit.SECONDS.sleep(1);
            return String.valueOf(RandomUtils.nextInt(1,100));
        }
    }
}
