package xzy.dxc;
import org.junit.Test;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class ThreadLocalTest {
    private static final AtomicInteger nextId = new AtomicInteger(0);
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };


    @Test
    public void test1() throws InterruptedException {
        for(int i = 0 ; i < 5 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("threadName=" + Thread.currentThread().getName() + ",threadId=" + threadId.get());
                    threadId.get();
                }
            },"线程" + i).start();
        }
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void test2(){
        threadId.get();
        threadId.set(3);
    }
}
