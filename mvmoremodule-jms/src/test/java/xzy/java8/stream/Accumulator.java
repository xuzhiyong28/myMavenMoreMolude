package xzy.java8.stream;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuzhiyong
 * @createDate 2019-09-05-22:15
 */
public class Accumulator {
    public long total = 0;
    public AtomicLong atomicLong = new AtomicLong(0);

    public void add(long value) {
        total += value;
    }

    public void addCAS(long value){
        atomicLong.addAndGet(value);
    }
}
