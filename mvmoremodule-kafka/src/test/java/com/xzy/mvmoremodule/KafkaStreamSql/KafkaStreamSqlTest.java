package com.xzy.mvmoremodule.KafkaStreamSql;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class KafkaStreamSqlTest {
    @Test
    public void test() throws InterruptedException {
        List<Runnable> jobs = Arrays.asList(new RandomNetworkDataProducer() , new NetworkDataProcessor());
        jobs.parallelStream().map(Thread::new).forEach(Thread::run);
        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
