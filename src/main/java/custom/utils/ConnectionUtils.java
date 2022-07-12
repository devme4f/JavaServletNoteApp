package custom.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    public static Connection getMariaDBConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "dev";
        String username = "devme";
        String password = "password";
        return getMariaDBConnection(hostName, dbName, username, password);
    }

    public static Connection getMariaDBConnection(String hostName, String dbName, String username, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        String jdbcURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        return DriverManager.getConnection(jdbcURL, username, password);
    }

}
