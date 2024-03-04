package model.common;


import java.sql.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Common {
    public static Timestamp convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        java.util.Date utilDate = java.util.Date.from(localDateTime.toInstant(ZoneOffset.UTC));
        return new Timestamp(utilDate.getTime());
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

    public static Double calcTotal(Timestamp checkInDate, Timestamp checkOutDate, Double price) {
        long differenceInMillis = checkOutDate.getTime() - checkInDate.getTime();
        Double daysDifference = (double) (differenceInMillis / (1000 * 60 * 60 * 24));
        return price * daysDifference;
    }
}
