package org.example.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection conn;

    public static Connection getConn() {
        if (conn == null) {
            conectar();
        }
        return conn;
    }

    private static void conectar() {
        Properties propriedades = getPropriedades();
        String url = propriedades.getProperty("dburl");

        try {
            conn = DriverManager.getConnection(url, propriedades);
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void fecharConn() {
        try {
            if (conn != null) {
                conn.close();
            }
        }
        catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    private static Properties getPropriedades() {
        try (FileInputStream file = new FileInputStream("config/db.properties")) {
            Properties properties = new Properties();
            properties.load(file);
            return properties;
        }
        catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

}
