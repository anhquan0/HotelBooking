package model.dao;

import model.entity.Customer;
import model.entity.Service;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceServiceDAO {
    Connection connection;


    public InvoiceServiceDAO(Connection connection) {
        this.connection = connection;
    }

    public InvoiceServiceDAO() {
    }

    public boolean createInvoiceService(Integer invoiceID, Integer serviceID, Customer customer) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO InvoiceService (InvoiceID, ServiceID, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());
                preparedStatement.setInt(1, invoiceID);
                preparedStatement.setInt(2, serviceID);
                preparedStatement.setDate(3, currentDate);
                preparedStatement.setString(4, customer.getName());
                preparedStatement.setDate(5, currentDate);
                preparedStatement.setString(6, customer.getName());
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
