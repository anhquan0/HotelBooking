package model.dao;

import model.entity.Customer;
import model.entity.Service;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    Connection connection = DBConnect.getConnection();

    public Customer findByAccountId(int accountId) {
        Customer customer = null;
        if(connection != null) {
            try {
                String sql = "SELECT c.* FROM Customer c JOIN Account a ON c.CustomerID = a.CustomerID WHERE AccountID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, accountId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    customer = new Customer();
                    customer.setCustomerId(resultSet.getInt("CustomerID"));
                    customer.setName(resultSet.getString("Name"));
                    customer.setEmail(resultSet.getString("Email"));
                    customer.setPhone(resultSet.getString("Phone"));
                    customer.setPersonalInfo(resultSet.getString("PersonalInfo"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
}
