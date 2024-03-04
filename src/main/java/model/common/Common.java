package model.common;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Common {
    public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        java.util.Date utilDate = java.util.Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        return new Date(utilDate.getTime());
    }

    public static void closeResources(ResultSet rs, PreparedStatement preparedStatement, Connection connection) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
