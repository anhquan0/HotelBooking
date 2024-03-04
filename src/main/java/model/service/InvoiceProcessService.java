package model.service;

import model.dao.InvoiceDAO;
import model.dao.InvoiceServiceDAO;
import model.dao.ServiceDAO;
import model.entity.Account;
import model.entity.Customer;
import util.DBConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceProcessService {
    Connection connection;

    public boolean createInvoice(LocalDateTime checkInTime, LocalDateTime checkOutTime, String[] serviceIds, Integer roomID, String paymentMethod, String note, Customer customer) {
        boolean flag = true;
        if (connection != null) {
            try {
                connection = DBConnect.getConnection();
                connection.setAutoCommit(false);
                InvoiceDAO invoiceDAO = new InvoiceDAO(connection);
                InvoiceServiceDAO invoiceServiceDAO = new InvoiceServiceDAO(connection);
                Integer invoiceId = invoiceDAO.createInvoice(customer, roomID, paymentMethod, note, checkInTime, checkOutTime);
                if(invoiceId != 0) {
                    if(serviceIds.length != 0) {
                        List<Integer> serviceIdList = Arrays.stream(serviceIds).map(Integer::parseInt).collect(Collectors.toList());
                        for(Integer serviceId: serviceIdList) {
                            if(!invoiceServiceDAO.createInvoiceService(invoiceId, serviceId, customer)) {
                                throw new SQLException("Invoice Service Error");
                            }
                        }
                    }
                } else {
                    throw new SQLException("Invoice Error");
                }
            } catch (SQLException e) {
                try {
                    flag = false;
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } finally {
                // Đóng kết nối
                try {
                    connection.setAutoCommit(true); // Đặt lại auto-commit cho connection
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return flag;
    }
}
