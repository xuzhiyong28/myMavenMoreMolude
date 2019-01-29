/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.main;

import io.shardingsphere.core.api.HintManager;

import javax.sql.DataSource;
import java.sql.*;

public class YamldataRepository {

    private final DataSource dataSource;

    public YamldataRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void demo() throws SQLException {
        //createTable();
        //insertData();
        //System.out.println("1.Query with EQUAL--------------");
        queryWithEqual();
        //System.out.println("2.Query with IN--------------");
        //queryWithIn();
        //System.out.println("3.Query with Hint--------------");
        //queryWithHint();
        //System.out.println("4.Drop tables--------------");
        //dropTable();
        //System.out.println("5.All done-----------");
    }

    private void createTable() throws SQLException {
        execute("CREATE TABLE IF NOT EXISTS t_order (order_id BIGINT NOT NULL, createdate VARCHAR(50))");
    }

    private void insertData() throws SQLException {
        execute("INSERT INTO t_order (order_id, createdate) VALUES (10, '20181212')");
    }

    private long insertAndGetGeneratedKey(final String sql) throws SQLException {
        long result = -1;
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    result = resultSet.getLong(1);
                }
            }
        }
        return result;
    }

    private void queryWithEqual() throws SQLException {
        String sql = "select * from t_order where createdate = '20181212' and order_id = 10";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //printQuery(preparedStatement);
        }
    }

    private void queryWithIn() throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id IN (?, ?)";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, 10);
            preparedStatement.setInt(2, 11);
            printQuery(preparedStatement);
        }
    }

    private void queryWithHint() throws SQLException {
        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id";
        try (
                HintManager hintManager = HintManager.getInstance();
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            hintManager.addDatabaseShardingValue("t_order", "user_id", 11);
            //printQuery(preparedStatement);
        }
    }

    private void printQuery(final PreparedStatement preparedStatement) throws SQLException {
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                System.out.print("order_item_id:" + resultSet.getLong(1) + ", ");
                System.out.print("order_id:" + resultSet.getLong(2) + ", ");
                System.out.print("user_id:" + resultSet.getInt(3));
                System.out.println();
            }
        }
    }

    private void dropTable() throws SQLException {
        execute("DROP TABLE t_order_item");
        execute("DROP TABLE t_order");
    }

    private void execute(final String sql) throws SQLException {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
