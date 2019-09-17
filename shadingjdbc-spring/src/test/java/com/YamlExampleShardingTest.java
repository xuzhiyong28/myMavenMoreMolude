package com;

import io.shardingsphere.core.api.yaml.YamlShardingDataSourceFactory;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class YamlExampleShardingTest {
    private static final String YAML_FILE = "classpath:sharding-custom.yaml";
    public static DataSource getDataSource() throws IOException, SQLException {
        return YamlShardingDataSourceFactory.createDataSource(ResourceUtils.getFile(YAML_FILE));
    }


    public static JdbcTemplate getJdbcTemple(){
        DataSource dataSource = null;
        try {
            dataSource = getDataSource();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JdbcTemplate(dataSource);
    }


    @Test
    public void test(){
        JdbcTemplate jdbcTemplate = getJdbcTemple();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flow where flowtime in ('20170818','20190205')");
        System.out.println(list);
    }

}
