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

import javax.annotation.Resource;
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
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateTable() {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS flow (flowtime VARCHAR(50) NOT NULL, value INT NOT NULL, PRIMARY KEY (flowtime))");

        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS ips (flowtime VARCHAR(50) NOT NULL, value INT NOT NULL)");
        //不做分片的表
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS websocket (flowtime VARCHAR(50) NOT NULL, value INT NOT NULL, PRIMARY KEY (flowtime))");
    }

    @Test
    public void testInsertOne(){
        //测试一条记录多条插入
        jdbcTemplate.update("INSERT IGNORE INTO flow(flowtime,value) VALUES ('20190525',1),('20190526',2),('20190527',2)");
    }

    @Test
    public void testInsert() {
        List<String> times = getRandomTime(1000);
        for (String time : times) {
            String value = RandomUtils.nextInt(0, 10000) + "";
            jdbcTemplate.update("INSERT IGNORE INTO flow(flowtime,value) VALUES ('" + time + "', " + value + ")");
            //不进行分片的插入
            jdbcTemplate.update("INSERT IGNORE INTO websocket(flowtime,value) VALUES ('" + time + "', " + value + ")");
        }
    }


    @Test
    public void query(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flow where flowtime = '20170818'");
    }

    @Test
    public void query2(){
        jdbcTemplate.queryForList("select * from flow where flowtime in ('20170818','20190205')");
    }


    @Test
    public void drop(){
        jdbcTemplate.update("drop table flow");
    }

    @Test
    public void delete(){
        jdbcTemplate.update("delete from flow");
        jdbcTemplate.update("delete from ips");
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
