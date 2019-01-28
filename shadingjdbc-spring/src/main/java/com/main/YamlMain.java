package com.main;/**
 * Created by Administrator on 2019-01-28.
 */

import io.shardingsphere.core.api.yaml.YamlShardingDataSourceFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author xuzhiyong
 * @createDate 2019-01-28-21:56
 */
public class YamlMain {

    public static void main(String[] args) throws IOException, SQLException {
        DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(getYamlFile());
        YamldataRepository yamldataRepository = new YamldataRepository(dataSource);
        yamldataRepository.demo();
    }

    private static File getYamlFile() throws IOException {
        Resource resource = new ClassPathResource("sharding-custom.yaml");
        return resource.getFile();
    }

}
