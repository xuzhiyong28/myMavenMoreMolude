package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-database.xml", "classpath:applicationContext-sharding.xml"})
public class ShardingTest2 {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateTable() {
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS acca (flowtime VARCHAR(50) NOT NULL,dataType VARCHAR(10) NOT NULL,value INT NOT NULL)");
    }

    @Test
    public void testInsert() {
        jdbcTemplate.update("INSERT INTO acca(flowtime,dataType,value) VALUES ('20180101','1','12')");
    }


}
