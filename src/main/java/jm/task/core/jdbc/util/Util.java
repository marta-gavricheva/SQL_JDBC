package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Util {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String password = "15042021";
    private static final String username = "root";

 private Util(){}

 public static Connection  getConnection() {
            try {
                return DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
return  getConnection();
    }

}
