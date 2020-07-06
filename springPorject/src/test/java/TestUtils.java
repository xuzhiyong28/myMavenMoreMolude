import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class TestUtils {
    public static DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("chenhao1991@");
        return dataSource;
    }
}
