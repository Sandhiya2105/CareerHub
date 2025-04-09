package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
    public static Connection getConnection() {
        try {
            Properties props = DBPropertyUtil.loadProperties("db.properties");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.err.println(" Failed to connect to DB: " + e.getMessage());
            return null;
        }
    }
}

