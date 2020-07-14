import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {

    @Test
    public void test1() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf-8", "root", "123456");
        String sql = "delete from book";
        Statement stmt = conn.createStatement();
        conn.setAutoCommit(false);
        //3、执行并保存结果集
        int rows = stmt.executeUpdate(sql);
        System.out.println("受影响的行： "+rows);
        conn.commit();
        conn.close();
        stmt.close();
    }

}
