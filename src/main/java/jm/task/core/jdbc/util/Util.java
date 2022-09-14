package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    public static final String URL = "jdbc:mysql://localhost:3306/task113";
    public static final String DB_USER = "admin";
    public static final String DB_PASSW = "***";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSW);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return connection;
    }


}
