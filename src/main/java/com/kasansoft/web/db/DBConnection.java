package com.kasansoft.web.db;

import com.kasansoft.web.util.ApplicationProperties;

import java.sql.Connection;
import java.sql.DriverManager;

import static java.lang.Class.forName;

public class DBConnection {
    private static  Connection connection;

    public static Connection getConnection() throws Exception {
        ApplicationProperties properties = ApplicationProperties.getInstance();
        Class.forName(properties.get("sql.connection.Driver"));
        connection= DriverManager.getConnection(
            properties.get("sql.connection.url"),
            properties.get("sql.connection.username"),
            properties.get("sql.connection.password")
        );
        return connection;
    }
}
