package com;

import io.shardingsphere.core.api.yaml.YamlShardingDataSourceFactory;
import io.shardingsphere.jdbc.orchestration.api.OrchestrationShardingDataSourceFactory;
import io.shardingsphere.jdbc.orchestration.config.OrchestrationConfiguration;
import io.shardingsphere.jdbc.orchestration.config.OrchestrationType;
import io.shardingsphere.orchestration.reg.zookeeper.ZookeeperConfiguration;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    public static CuratorFramework getCuratorFramework(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework cf = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        cf.getConnectionStateListenable().addListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if (connectionState == ConnectionState.LOST) {
                    System.out.println("连接丢失");//连接丢失
                } else if (connectionState == ConnectionState.CONNECTED) {
                    System.out.println("成功连接");
                } else if (connectionState == ConnectionState.RECONNECTED) {
                    System.out.println("重连成功");
                }
            }
        });
        return cf;
    }


    @Test
    public void test(){
        JdbcTemplate jdbcTemplate = getJdbcTemple();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flow where flowtime in ('20170818','20190205')");
        System.out.println(list);
    }



    /****
     * 初始化datasource数据
     */
    @Test
    public void initDataSourceZkData() throws Exception {
        CuratorFramework cf = getCuratorFramework();
        cf.start();
        //创建datasource信息 orchestration-sharding-data-source为命名空间 ，闯将的datasource是ymal模式的
        //Map<String, DataSource> result = DataSourceConverter.dataSourceMapFromYaml(this.regCenter.getDirectly(this.configNode.getFullPath("config/datasource")));
        String dataSource = "" +
                "  dataSource_default: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_default?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2017: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2017?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2018: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2018?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2019: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2019?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2020: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2020?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000";
        cf.create()
                .creatingParentsIfNeeded() //自动创建父节点
                .withMode(CreateMode.PERSISTENT) //设置成永久节点
                .forPath("/sharding-data/xuzy/config/datasource", dataSource.getBytes());
        cf.close();
    }


    /***
     * 更新datasource数据
     */
    @Test
    public void updateDataSourceZkData() throws Exception {
        CuratorFramework cf = getCuratorFramework();
        cf.start();
        String dataSource = "  dataSource_default: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_default?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2017: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2017?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2018: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2018?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2019: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2019?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2020: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2020?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000\n" +
                "  dataSource_2021: !!org.apache.commons.dbcp.BasicDataSource\n" +
                "    driverClassName: com.mysql.jdbc.Driver\n" +
                "    url: jdbc:mysql://localhost:3306/sharding_2021?useUnicode=true&amp;characterEncoding=utf-8\n" +
                "    username: root\n" +
                "    password: 123456\n" +
                "    initialSize : 0\n" +
                "    maxActive : 200\n" +
                "    maxIdle : 20\n" +
                "    minIdle : 1\n" +
                "    maxWait : 60000";
        //更新
        cf.setData().forPath("/sharding-data/xuzy/config/datasource", dataSource.getBytes());
    }

    /***
     * 初始化rule数据
     * @throws Exception
     */
    @Test
    public void initRuleZkData() throws Exception {
        CuratorFramework cf = getCuratorFramework();
        cf.start();
        String rule = "  tables:\n" +
                "    flow:\n" +
                "      actualDataNodes: dataSource_${2017..2020}.flow_0${1..9},dataSource_${2017..2020}.flow_1${0..2}\n" +
                "      tableStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloTableShardingAlgorithm\n" +
                "      databaseStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloDatabaseShardingAlgorithm\n" +
                "    ips:\n" +
                "      actualDataNodes: dataSource_${2017..2020}.ips_0${1..9},dataSource_${2017..2020}.ips_1${0..2}\n" +
                "      tableStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloTableShardingAlgorithm\n" +
                "      databaseStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloDatabaseShardingAlgorithm\n" +
                "    acca:\n" +
                "      actualDataNodes: dataSource_${2017..2020}.acca_0${1..9},dataSource_${2017..2020}.acca_1${0..2}\n" +
                "      tableStrategy:\n" +
                "        complex:\n" +
                "          shardingColumns: flowtime,dataType\n" +
                "          algorithmClassName: com.shardingAlgorithm.ComplexModuloTableShardingAlgorithm\n" +
                "      databaseStrategy:\n" +
                "        complex:\n" +
                "          shardingColumns: flowtime,dataType\n" +
                "          algorithmClassName: com.shardingAlgorithm.ComplexModuloDatabaseShardingAlgorithm\n" +
                "  bindingTables:\n" +
                "    - flow,ips,acca\n" +
                "  defaultDatabaseStrategy:\n" +
                "    none:\n" +
                "  defaultTableStrategy:\n" +
                "    none:";
        cf.create()
                .creatingParentsIfNeeded() //自动创建父节点
                .withMode(CreateMode.PERSISTENT) //设置成永久节点
                .forPath("/sharding-data/xuzy/config/sharding/rule", rule.getBytes());
        cf.close();
    }

    @Test
    public void updateRuleZkData() throws Exception {
        CuratorFramework cf = getCuratorFramework();
        cf.start();
        String rule = "  tables:\n" +
                "    flow:\n" +
                "      actualDataNodes: dataSource_${2017..2021}.flow_0${1..9},dataSource_${2017..2021}.flow_1${0..2}\n" +
                "      tableStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloTableShardingAlgorithm\n" +
                "      databaseStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloDatabaseShardingAlgorithm\n" +
                "    ips:\n" +
                "      actualDataNodes: dataSource_${2017..2020}.ips_0${1..9},dataSource_${2017..2020}.ips_1${0..2}\n" +
                "      tableStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloTableShardingAlgorithm\n" +
                "      databaseStrategy:\n" +
                "        standard:\n" +
                "          shardingColumn: flowtime\n" +
                "          preciseAlgorithmClassName: com.shardingAlgorithm.PreciseModuloDatabaseShardingAlgorithm\n" +
                "    acca:\n" +
                "      actualDataNodes: dataSource_${2017..2020}.acca_0${1..9},dataSource_${2017..2020}.acca_1${0..2}\n" +
                "      tableStrategy:\n" +
                "        complex:\n" +
                "          shardingColumns: flowtime,dataType\n" +
                "          algorithmClassName: com.shardingAlgorithm.ComplexModuloTableShardingAlgorithm\n" +
                "      databaseStrategy:\n" +
                "        complex:\n" +
                "          shardingColumns: flowtime,dataType\n" +
                "          algorithmClassName: com.shardingAlgorithm.ComplexModuloDatabaseShardingAlgorithm\n" +
                "  bindingTables:\n" +
                "    - flow,ips,acca\n" +
                "  defaultDatabaseStrategy:\n" +
                "    none:\n" +
                "  defaultTableStrategy:\n" +
                "    none:";
        //更新
        cf.setData().forPath("/sharding-data/xuzy/config/sharding/rule", rule.getBytes());
    }

    /***
     * 初始化sharding-prop数据
     * @throws Exception
     */
    @Test
    public void initPropZkData() throws Exception {
        CuratorFramework cf = getCuratorFramework();
        cf.start();
        String prop = "{\"sql.show\":true}";
        cf.create()
                .creatingParentsIfNeeded() //自动创建父节点
                .withMode(CreateMode.PERSISTENT) //设置成永久节点
                .forPath("/sharding-data/xuzy/config/sharding/props", prop.getBytes());
        cf.close();
    }


    @Test
    public void deleteZkDataSourceAndRule() throws Exception {
        CuratorFramework cf = getCuratorFramework();
        cf.start();
        Stat stat = cf.checkExists().forPath("/sharding-data/xuzy/config/datasource");
        if(stat != null){
            cf.delete().forPath("/sharding-data/xuzy/config/datasource");
        }
        Stat stat2 = cf.checkExists().forPath("/sharding-data/xuzy/config/sharding/rule");
        if(stat2 != null){
            cf.delete().forPath("/sharding-data/xuzy/config/sharding/rule");
        }
        cf.close();
    }

    /***
     * 获取编排治理的Datasource
     * @return
     * @throws SQLException
     */
    public static DataSource getOrchestrationDataSource() throws SQLException {
        ZookeeperConfiguration regConfig = new ZookeeperConfiguration();
        regConfig.setServerLists("localhost:2181");
        regConfig.setNamespace("sharding-data");
        OrchestrationConfiguration orchConcifg = new OrchestrationConfiguration("xuzy",regConfig,false, OrchestrationType.SHARDING);
        DataSource dataSource = OrchestrationShardingDataSourceFactory.createDataSource(orchConcifg);
        return dataSource;
    }

    public static JdbcTemplate getOrchestrationJdbcTemple(){
        DataSource dataSource = null;
        try {
            dataSource = getOrchestrationDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new JdbcTemplate(dataSource);
    }


    @Test
    public void testOrchestration() throws InterruptedException {
        JdbcTemplate jdbcTemplate = getOrchestrationJdbcTemple();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flow where flowtime in ('20170818','20190205')");
        System.out.println(list);

    }

    @Test
    public void testOrchestration2() throws Exception {

        deleteZkDataSourceAndRule();
        initDataSourceZkData();
        initRuleZkData();

        ZookeeperConfiguration regConfig = new ZookeeperConfiguration();
        regConfig.setServerLists("localhost:2181");
        regConfig.setNamespace("sharding-data");
        OrchestrationConfiguration orchConcifg = new OrchestrationConfiguration("xuzy",regConfig,false, OrchestrationType.SHARDING);
        DataSource dataSource = OrchestrationShardingDataSourceFactory.createDataSource(orchConcifg);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from flow where flowtime in ('20170818','20190205')");


        updateDataSourceZkData(); //zk修改datasource值，触发watch
        updateRuleZkData(); //zk修改rule值，触发watch
        TimeUnit.SECONDS.sleep(20);

        List<Map<String, Object>> listNew = jdbcTemplate.queryForList("select * from flow where flowtime in ('20170818','20190205','20210405')");
        System.out.println(listNew);
    }




}
