package com;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:applicationContext-database-master-slave.xml", "classpath:applicationContext-sharding-master-slave.xml"})
public class ShardingTestMasterSlaveTest {
    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
}
