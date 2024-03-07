package com.ohgiraffers.common.JDBC;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;

import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class template {

    public static Connection getConnection() {

        Connection con = null;
        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url, prop);         // ohgiraffers 계정과 연결된다.

        } catch (IOException e) {
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return con;
    }

    ////////////////// 얘 close 닫아주는건데 왜/어떻게 만들었는지 확실하게 재공부!!! ↓ //////////////////

    public static void close(Statement stmt) {                  // 1. Statement stmt

        try {
            if (stmt != null && !stmt.isClosed()) {

                stmt.close();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    public static void close(ResultSet rset) {              // 2. ResultSet rset

        try {
            if (rset != null && !rset.isClosed()) {

                rset.close();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    public static void close(Connection con) {          // 3. Connection con

        try {
            if (con != null && !con.isClosed()) {

                con.close();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
}