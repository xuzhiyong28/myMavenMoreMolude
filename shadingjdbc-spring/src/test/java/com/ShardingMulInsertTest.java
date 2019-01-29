package com;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.util.SpringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/***
 * 测试并发写入和单线程写入的性能
 */
public class ShardingMulInsertTest {

    private static final int INSERT_SIZE = 12000;
    private static final int BATCH = 10; //线程的执行批次


    public static void main(String[] args) throws InterruptedException {
        ShardingMulInsertTest test = new ShardingMulInsertTest();
        //test.insertMul();
        //test.insertSingle();
        test.insertSingleDefault();
    }

    /****
     * 多线程 sharding 插入数据
     */
    public void insertMul() throws InterruptedException {
        deleteTable("ips");
        StopWatch watch = new StopWatch();
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtils.getBean("jdbcTemplate");
        List<String> times = getRandomTime(INSERT_SIZE);
        int length = INSERT_SIZE / BATCH; //每个批次执行的个数
        ExecutorService executorService = Executors.newFixedThreadPool(BATCH);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        watch.start();
        for (int i = 1; i <= BATCH; i++) {
            executorService.submit(new Mycallable(countDownLatch, times.subList((i - 1) * length, (i * length) - 1), jdbcTemplate));
        }
        executorService.shutdown();
        countDownLatch.await();
        System.out.println("多线程 sharding 插入数据耗时:" + watch.getTime());
    }

    /***
     * 单线程 sharding 写入 数据
     */
    public void insertSingle() {
        deleteTable("ips");
        StopWatch watch = new StopWatch();
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtils.getBean("jdbcTemplate");
        List<String> times = getRandomTime(INSERT_SIZE);
        int length = INSERT_SIZE / BATCH; //每个批次执行的个数
        watch.start();
        for (int i = 1; i <= BATCH; i++) {
            List<String> timeTmpList = times.subList((i - 1) * length, (i * length) - 1);
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT IGNORE INTO ips(flowtime,value) VALUES ");
            for (String time : timeTmpList) {
                sb.append("('" + time + "'," + RandomUtils.nextInt(0, 2000) + "),");
            }
            String sql = StringUtils.chop(sb.toString());
            jdbcTemplate.update(sql);
        }
        System.out.println("单线程sharding写入数据耗时:" + watch.getTime());
    }

    /***
     * 普通jdbc插入耗时
     */
    public void insertSingleDefault(){
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtils.getBean("jdbcTemplate_default");
        jdbcTemplate.update("delete from websocket");
        StopWatch watch = new StopWatch();
        List<String> times = getRandomTime(INSERT_SIZE);
        int length = INSERT_SIZE / BATCH; //每个批次执行的个数
        watch.start();
        for (int i = 1; i <= BATCH; i++) {
            List<String> timeTmpList = times.subList((i - 1) * length, (i * length) - 1);
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT IGNORE INTO websocket(flowtime,value) VALUES ");
            for (String time : timeTmpList) {
                sb.append("('" + time + "'," + RandomUtils.nextInt(0, 2000) + "),");
            }
            String sql = StringUtils.chop(sb.toString());
            jdbcTemplate.update(sql);
        }
        System.out.println("普通jdbc插入耗时:" + watch.getTime());
    }



    //删除表数据
    public void deleteTable(String tableName) {
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtils.getBean("jdbcTemplate");
        jdbcTemplate.update("delete from " + tableName);
    }


    /***
     * 获得可重复的随机的时间
     * @return
     */
    public static List<String> getRandomTime(int size) {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            String year = RandomUtils.nextInt(2017, 2021) + "";
            String month = RandomUtils.nextInt(0, 2) == 0 ? ("0" + RandomUtils.nextInt(1, 10)) : (RandomUtils.nextInt(10, 13) + "");
            String day = RandomUtils.nextInt(0, 2) == 0 ? ("0" + RandomUtils.nextInt(1, 10)) : (RandomUtils.nextInt(10, 31) + "");
            String time = year + month + day;
            list.add(time);
        }
        return list;
    }


    public static class Mycallable implements Callable<Boolean> {
        private CountDownLatch countDownLatch;
        private List<String> times;
        private JdbcTemplate jdbcTemplate;

        public Mycallable(CountDownLatch countDownLatch, List<String> times, JdbcTemplate jdbcTemplate) {
            this.countDownLatch = countDownLatch;
            this.times = times;
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Boolean call() throws Exception {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("INSERT IGNORE INTO ips(flowtime,value) VALUES ");
                for (String time : times) {
                    sb.append("('" + time + "'," + RandomUtils.nextInt(0, 2000) + "),");
                }
                String sql = StringUtils.chop(sb.toString());
                jdbcTemplate.update(sql);
            } catch (Exception e) {

            } finally {
                countDownLatch.countDown();
            }
            return true;
        }
    }
}
