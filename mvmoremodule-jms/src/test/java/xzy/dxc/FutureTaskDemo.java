package xzy.dxc;

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
}
