package xzy.java8;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/**
 * @author xuzhiyong
 * @createDate 2019-08-01-21:37
 */
public class ClockTest {
    @Test
    public void test(){
        Clock clock = Clock.fixed(Instant.ofEpochSecond(3600), ZoneId.systemDefault());
        System.out.println("clock = " + clock);
    }
}
