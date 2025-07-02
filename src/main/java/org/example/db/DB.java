package org.example.db;

import org.example.exceptions.DBException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn;

    public static Connection getConn() {
        if (conn == null) {
            connect();
        }
        return conn;
    }

    private static void connect() {
        Properties properties = getProperties();
        String url = properties.getProperty("dburl");

        try {
            conn = DriverManager.getConnection(url, properties);
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private static Properties getProperties() {
        try (FileInputStream file = new FileInputStream("config/db.properties")) {
            Properties properties = new Properties();
            properties.load(file);
            return properties;
        }
        catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

}
