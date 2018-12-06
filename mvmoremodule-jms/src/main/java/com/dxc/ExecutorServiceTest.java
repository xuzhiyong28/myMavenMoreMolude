package com.dxc;/**
 * Created by Administrator on 2018-06-05.
 */

import java.util.concurrent.*;

/**
 * @author xuzhiyong
 * @createDate 2018-06-05-10:46
 */
public class ExecutorServiceTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for(int i = 0 ; i < 10 ; i++){
            executorService.submit(new Mycallable(countDownLatch));
        }
        countDownLatch.await();
        System.out.println("xxxx");
        executorService.shutdown();
    }


    public static class Mycallable implements Callable<Boolean>{
        private CountDownLatch countDownLatch;
        public Mycallable(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        @Override
        public Boolean call() throws Exception {
            System.out.println("do some");
            TimeUnit.SECONDS.sleep(5);
            countDownLatch.countDown();
            return true;
        }
    }

}
