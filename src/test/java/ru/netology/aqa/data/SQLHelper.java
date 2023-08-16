package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
    }

    private static final String url = System.getProperty("db.url");
    private static final String user = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    @SneakyThrows
    public static String findPayStatus() {
        String statusSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        return getData(statusSQL);
    }

    @SneakyThrows
    public static String findCreditStatus() {
        String statusSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        return getData(statusSQL);
    }


    @SneakyThrows
    private static String getData(String query) {
        QueryRunner runner = new QueryRunner();
        String data = "";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            data = runner.query(conn, query, new ScalarHandler<>());
        }
        return data;
    }

    @SneakyThrows
    public static long getOrderTransactionCount() {
        String countSQL = "SELECT COUNT(*) FROM order_entity;";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            QueryRunner runner = new QueryRunner();
            Long count = runner.query(conn, countSQL, new ScalarHandler<>());
            return count != null ? count : 0;
        }
    }
}