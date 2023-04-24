package com.murmylo.volodymyr.confinement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//example of a jdbc connection for each thread
public class JdbcConnectionPerThread {
    private static ThreadLocal<Connection> connectionHolder
            = ThreadLocal.withInitial(() -> {
        try {
            return DriverManager.getConnection("fake_url");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    });

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}
