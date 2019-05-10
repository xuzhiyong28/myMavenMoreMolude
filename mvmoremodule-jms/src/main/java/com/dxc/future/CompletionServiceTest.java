package com.dxc.future;

import java.util.Random;
import java.util.concurrent.*;

/****
 * ExecutorCompletionService 是简介版的Future+线程池
 * 比遍历future.get()的好处是他会先取得完成成功的结果，内部是一个队列，将先完成的放到队列中然后我们获取，这样我们就可以将先完成的结果先取得
 * 先完成的必定先被取出。这样就减少了不必要的等待时间
 * 代码上也更简洁
 */
public class CompletionServiceTest {

    public static void main(String agrs[]) throws InterruptedException,ExecutionException {
        int numThread = 100;
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPoolExecutor);
        for(int i = 0 ; i < numThread ; i++){
            completionService.submit(new Task());
        }

        for(int i = 0 ; i < numThread; i++){
            /*try {
                System.out.println(completionService.take().get(10,TimeUnit.SECONDS));
            } catch (TimeoutException e) {
                e.printStackTrace();
            }*/
            System.out.println(completionService.take().get());
        }
    }

    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            Random random = new Random();
            int result = random.nextInt(100);
            System.out.println("result = " + result);
            return result;
        }
    }
}
