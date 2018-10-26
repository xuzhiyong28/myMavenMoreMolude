package xzy.guava;

import com.google.common.base.Stopwatch;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class StopwatchTest {
    @Test
    public void test1(){
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
        stopwatch.reset();
        stopwatch.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
