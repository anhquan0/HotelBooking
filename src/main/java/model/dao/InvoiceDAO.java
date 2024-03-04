package model.dao;

import model.common.Common;
import model.entity.Customer;
import model.entity.Invoice;
import model.entity.Service;
import util.DBConnect;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    Connection connection;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public InvoiceDAO(Connection connection) {
        this.connection = connection;
    }

    public InvoiceDAO() {
        this.connection = DBConnect.getConnection();
    }

    public Integer createInvoice(Customer customer, Integer roomID, String paymentMethod, String note, LocalDateTime checkInTime, LocalDateTime checkOutTime) throws SQLException {
        Integer invoiceId = 0;
        if (connection != null && !duplicateCheck(checkInTime, checkOutTime, roomID)) {
            try {
                String sql = "INSERT INTO Invoice (CustomerID, RoomID, CheckInDate, CheckOutDate, PaymentMethod, Note, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setInt(2, roomID);
                preparedStatement.setTimestamp(3, Common.convertLocalDateTimeToDate(checkInTime));
                preparedStatement.setTimestamp(4, Common.convertLocalDateTimeToDate(checkOutTime));
                preparedStatement.setString(5, paymentMethod);
                preparedStatement.setString(6, note);
                preparedStatement.setDate(7, currentDate);
                preparedStatement.setString(8, customer.getName());
                preparedStatement.setDate(9, currentDate);
                preparedStatement.setString(10, customer.getName());
                preparedStatement.executeUpdate();
                connection.commit();

                String getInvoiceIdSQL = "SELECT TOP 1 * FROM Invoice ORDER BY InvoiceID DESC";
                preparedStatement = connection.prepareStatement(getInvoiceIdSQL);
                rs = preparedStatement.executeQuery();
                if(rs.next()) {
                    invoiceId = rs.getInt(1);
                }
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invoiceId;
    }



    public boolean updateInvoiceTotal(Integer invoiceID, Double total) {
        if(connection != null) {
            try {
                String sql = "UPDATE Invoice SET Total = ? WHERE InvoiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());

                preparedStatement.setDouble(1, total);
                preparedStatement.setInt(2, invoiceID);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean duplicateCheck(LocalDateTime checkInTime, LocalDateTime checkOutTime, Integer roomID) throws SQLException {
        if(connection != null) {
            String duplicateCheckSql = "SELECT COUNT(*) FROM Invoice WHERE RoomID = ? AND ((CheckInDate <= ? AND CheckOutDate >= ?) OR (CheckInDate >= ? AND CheckOutDate <= ?) OR (CheckInDate <= ? AND CheckOutDate >= ?))";
            PreparedStatement duplicateCheckStatement = connection.prepareStatement(duplicateCheckSql);
            duplicateCheckStatement.setInt(1, roomID);
            duplicateCheckStatement.setTimestamp(2, Common.convertLocalDateTimeToDate(checkInTime));
            duplicateCheckStatement.setTimestamp(3, Common.convertLocalDateTimeToDate(checkInTime));
            duplicateCheckStatement.setTimestamp(4, Common.convertLocalDateTimeToDate(checkInTime));
            duplicateCheckStatement.setTimestamp(5, Common.convertLocalDateTimeToDate(checkOutTime));
            duplicateCheckStatement.setTimestamp(6, Common.convertLocalDateTimeToDate(checkOutTime));
            duplicateCheckStatement.setTimestamp(7, Common.convertLocalDateTimeToDate(checkOutTime));
            ResultSet resultSet = duplicateCheckStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if(count > 0) return true;
        }
        return false;
    }

    public Invoice getSimpleInvoiceByID(Integer invoiceID) {
        Invoice invoice = new Invoice();
        try {
            String sql = "SELECT * FROM Invoice WHERE InvoiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Date currentDate = new Date(System.currentTimeMillis());

            preparedStatement.setInt(1, invoiceID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                invoice.setRoomId(resultSet.getInt("RoomID"));
                invoice.setTotal(resultSet.getDouble("Total"));
                invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

}
