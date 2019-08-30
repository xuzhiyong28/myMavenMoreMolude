package xzy.guava;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ListeningExecutorServiceTest {
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    public static void main(String[] args) throws InterruptedException {
        test2();
    }


    public static void test2() throws InterruptedException {
        int taskSize = 10;
        CountDownLatch countDownLatch = new CountDownLatch(taskSize);
        List<String> resultStr = Lists.newArrayList();
        for(int i = 0 ; i < taskSize ; i++){
            int finalI = i;
            ListenableFuture<String> future = service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    TimeUnit.SECONDS.sleep(1);
                    return "first_" + finalI;
                }
            });
            Futures.addCallback(future, new FutureCallback<String>() {
                @Override
                public void onSuccess(String s) {
                    countDownLatch.countDown();
                    resultStr.add(s);
                }
                @Override
                public void onFailure(Throwable throwable) {
                    System.err.println(throwable);
                }
            });
        }
        countDownLatch.await(30,TimeUnit.SECONDS);
        System.out.println(resultStr.size());
        //service.shutdown();
    }

    public static void test1(){
        //service.submit(() -> "first");
        ListenableFuture<String> future = service.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "first";
            }
        });
        Futures.addCallback(future, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("s = " + s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }
}
