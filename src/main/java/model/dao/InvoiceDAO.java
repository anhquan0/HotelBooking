package model.dao;

import model.common.Common;
import model.entity.Customer;
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

    public Integer createInvoice(Customer customer, Integer roomID, String paymentMethod, String note, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        Integer invoiceId = 0;
        if (connection != null) {
            try {
                String sql = "INSERT INTO Invoice (CustomerID, RoomID, CheckInDate, CheckOutDate, PaymentMethod, Note, CreatedDate, CreatedBy, UpdatedDate, UpdateBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setInt(2, roomID);
                preparedStatement.setDate(3, Common.convertLocalDateTimeToDate(checkInTime));
                preparedStatement.setDate(4, Common.convertLocalDateTimeToDate(checkOutTime));
                preparedStatement.setString(5, paymentMethod);
                preparedStatement.setString(6, note);
                preparedStatement.setDate(7, currentDate);
                preparedStatement.setString(8, customer.getName());
                preparedStatement.setDate(9, currentDate);
                preparedStatement.setString(10, customer.getName());
                preparedStatement.executeUpdate();

                rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    invoiceId = rs.getInt(1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Common.closeResources(rs, preparedStatement, connection);
            }
        }
        return invoiceId;
    }

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Service";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    Service service = new Service();
                    service.setServiceId(resultSet.getInt("ServiceID"));
                    service.setName(resultSet.getString("Name"));
                    service.setPrice(resultSet.getDouble("Price"));
                    service.setCreatedDate(resultSet.getDate("CreatedDate"));
                    service.setCreatedBy(resultSet.getString("CreatedBy"));
                    service.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    service.setUpdatedBy(resultSet.getString("UpdatedBy"));
                    services.add(service);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return services;
    }

    public Service getServiceByID(Integer serviceID) {
        Service service = null;

        if (connection != null) {
            try {
                String sql = "SELECT * FROM Service WHERE ServiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, serviceID);
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    service.setServiceId(resultSet.getInt("ServiceID"));
                    service.setName(resultSet.getString("Name"));
                    service.setPrice(resultSet.getDouble("Price"));
                    service.setCreatedDate(resultSet.getDate("CreatedDate"));
                    service.setCreatedBy(resultSet.getString("CreatedBy"));
                    service.setUpdatedDate(resultSet.getDate("UpdatedDate"));
                    service.setUpdatedBy(resultSet.getString("UpdatedBy"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return service;
    }


    public boolean updateInvoiceTotal(Integer invoiceID, Double total) {
        if(connection != null) {
            try {
                String sql = "UPDATE Invoice SET Total = ? WHERE InvoiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());

                preparedStatement.setDouble(1, total);
                preparedStatement.setInt(2, invoiceID);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
