package xzy.dxc;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final Callable<Integer> producer = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("!!!!!");
                return new Random().nextInt(1000);
            }
        };
        //FutureTask<Integer> futureTask = new FutureTask<>(producer);
        //futureTask.run();
        //futureTask.run();
        //System.out.println("主线程");
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            FutureTask<Integer> futureTask = new FutureTask<Integer>(producer){
                @Override
                public boolean isDone() {
                    System.out.println("处理成功!!!");
                    return super.isDone();
                }
            };
            es.submit(futureTask);
        }
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("!!!!");
            return "success";
        });

        Thread thread = new Thread(futureTask);
        thread.start();
        boolean isDone = futureTask.isDone();
        System.out.println("isDone =" + isDone);
        long l = System.currentTimeMillis();
        futureTask.get();
        System.out.println("isDone =" + isDone);
        System.out.println("耗时 =" + (System.currentTimeMillis() - l) + " ms");
    }


}
