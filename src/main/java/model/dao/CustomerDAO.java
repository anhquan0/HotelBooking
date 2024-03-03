package model.dao;

import model.entity.Service;
import util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    Connection connection = DBConnect.getConnection();

    public boolean createService(String serviceName, Double servicePrice, String createBy) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO Service (Name, Price, CreatedDate, UpdatedDate, CreatedBy, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());
                preparedStatement.setString(1, serviceName);
                preparedStatement.setDouble(2, servicePrice);
                preparedStatement.setDate(3, currentDate);
                preparedStatement.setDate(4, currentDate);
                preparedStatement.setString(5, createBy);
                preparedStatement.setString(6, createBy);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
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

    public boolean deleteService(Integer serviceID) {
        if (connection != null) {
            try {
                String sql = "DELETE FROM Service WHERE ServiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, serviceID);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean updateService(Integer serviceID, String serviceName, Double servicePrice, String updatedBy) {
        if(connection != null) {
            try {
                String sql = "UPDATE Service SET Name = ?, Price = ?, UpdatedBy = ?, UpdatedDate = ? WHERE ServiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                Date currentDate = new Date(System.currentTimeMillis());
                preparedStatement.setString(1, serviceName);
                preparedStatement.setDouble(2, servicePrice);
                preparedStatement.setString(3, updatedBy);
                preparedStatement.setDate(4, currentDate);
                preparedStatement.setInt(5, serviceID);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
