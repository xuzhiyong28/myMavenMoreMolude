package com.dxc.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class FutureTest2 {
    private static ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
    public static void main(String agrs[]) throws InterruptedException, ExecutionException {
        Integer result = 0;
        List<Callable<Integer>> futureList = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            futureList.add(new Task());
        }
        List<Future<Integer>> futures = threadPoolExecutor.invokeAll(futureList);
        for(int i = 0 ; i < 100 ; i++){
            threadPoolExecutor.submit(new Task());
        }
        for(Future<Integer> f : futures){
            result = result + f.get();
        }
        System.out.println(result);
        threadPoolExecutor.awaitTermination(100,TimeUnit.SECONDS);
        threadPoolExecutor.shutdown();
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            return new Integer(1);
        }
    }
}
