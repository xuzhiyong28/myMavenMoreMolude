package com;/**
 * Created by Administrator on 2019-01-28.
 */

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xuzhiyong
 * @createDate 2019-01-28-20:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:applicationContext-database.xml","classpath:applicationContext-sharding.xml"})
public class ShardingTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testCreateTable(){
        System.out.println(jdbcTemplate);
        jdbcTemplate.update("CREATE TABLE IF NOT EXISTS flow (time VARCHAR(50) NOT NULL, value INT NOT NULL, PRIMARY KEY (time))");
    }

    @Test
    public void testInsert(){
        String sql = "INSERT INTO flow(time,value) VALUES ('20180101', 123)";
        jdbcTemplate.update(sql);
    }

    @Test
    public void other(){
        System.out.println(StringUtils.substring("20180101",4,6));
    }

}
