package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-database-master-slave.xml", "classpath:applicationContext-sharding-master-slave.xml"})
public class ShardingTestMasterSlaveTest {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateTable() {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS edge (flowtime VARCHAR(50) NOT NULL, value INT NOT NULL)");
    }

    @Test
    public void testInsertOne(){
        //测试一条记录多条插入
        jdbcTemplate.update("INSERT IGNORE INTO edge(flowtime,value) VALUES ('20190525',1)");
    }


    @Test
    public void query(){
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from edge where flowtime = '20190525'");
        System.out.println(list);
    }

}
