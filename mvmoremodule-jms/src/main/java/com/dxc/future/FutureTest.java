package com.dxc.future;/**
 * Created by Administrator on 2018-07-25.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2018-07-25-9:56
 * Future小事例
 */
public class FutureTest {
    public static void main(String agrs[]) throws ExecutionException, InterruptedException {
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
        for(int i = 0 ; i < 100 ; i++){
            Future<Integer> f = threadPoolExecutor.submit(new Task());
            futureList.add(f);
        }

        int result = 0;
        for(Future<Integer> f  : futureList){
            result  += f.get();
        }
        System.out.println(result);



    }


    static class Task implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            Random random = new Random();
            int result = random.nextInt(100);
            System.out.println("result = " + result);
            return result;
        }
    }


}
