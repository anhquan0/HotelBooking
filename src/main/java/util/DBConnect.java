package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String url  = "jdbc:sqlserver://;serverName=localhost;databaseName=HotelBooking;encrypt=true;trustServerCertificate=true";
    private static final String user  = "sa";
    private static final String password  = "Anhquan012345";

    public static Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
