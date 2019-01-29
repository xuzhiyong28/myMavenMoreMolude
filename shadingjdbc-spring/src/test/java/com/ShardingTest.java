package com;/**
 * Created by Administrator on 2019-01-28.
 */

import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2019-01-28-20:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-database.xml", "classpath:applicationContext-sharding.xml"})
public class ShardingTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateTable() {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS flow (flowtime VARCHAR(50) NOT NULL, value INT NOT NULL, PRIMARY KEY (flowtime))");

        //不做分片的表
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS websocket (flowtime VARCHAR(50) NOT NULL, value INT NOT NULL, PRIMARY KEY (flowtime))");
    }

    @Test
    public void testInsert() {
        List<String> times = getRandomTime(1000);
        for (String time : times) {
            System.out.println("time = " + time);
            String value = RandomUtils.nextInt(0, 10000) + "";
            jdbcTemplate.update("INSERT IGNORE INTO flow(flowtime,value) VALUES ('" + time + "', " + value + ")");
            jdbcTemplate.update("INSERT IGNORE INTO websocket(flowtime,value) VALUES ('" + time + "', " + value + ")");
        }
    }

    @Test
    public void query(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flow where flowtime = '20170818'");
    }


    @Test
    public void other() {

    }

    /***
     * 获得不重复的随机的时间
     * @return
     */
    public List<String> getRandomTime(int size) {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            String year = RandomUtils.nextInt(2017, 2021) + "";
            String month = RandomUtils.nextInt(0, 2) == 0 ? ("0" + RandomUtils.nextInt(1, 10)) : (RandomUtils.nextInt(10, 13) + "");
            String day = RandomUtils.nextInt(0, 2) == 0 ? ("0" + RandomUtils.nextInt(1, 10)) : (RandomUtils.nextInt(10, 31) + "");
            String time = year + month + day;
            if (!list.contains(time)) {
                list.add(time);
            }
        }
        return list;
    }

}
