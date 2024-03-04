package model.service;

import model.common.Common;
import model.dao.InvoiceDAO;
import model.dao.InvoiceServiceDAO;
import model.dao.RoomDAO;
import model.dao.ServiceDAO;
import model.entity.*;
import util.DBConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceProcessService {
    Connection connection;

    ServiceDAO serviceDAO = new ServiceDAO();

    RoomDAO roomDAO = new RoomDAO();

    public boolean createInvoice(LocalDateTime checkInTime, LocalDateTime checkOutTime, String[] serviceIds, Integer roomID, String paymentMethod, String note, Customer customer) {
        boolean flag = true;
            try {
                connection = DBConnect.getConnection();
                connection.setAutoCommit(false);
                InvoiceDAO invoiceDAO = new InvoiceDAO(connection);
                InvoiceServiceDAO invoiceServiceDAO = new InvoiceServiceDAO(connection);
                Integer invoiceId = invoiceDAO.createInvoice(customer, roomID, paymentMethod, note, checkInTime, checkOutTime);
                if(invoiceId != 0) {
                    Double servicePriceTotal = 0.0;
                    if(serviceIds != null) {
                        List<Integer> serviceIdList = Arrays.stream(serviceIds).map(Integer::parseInt).collect(Collectors.toList());
                        for(Integer serviceId: serviceIdList) {
                            if(!invoiceServiceDAO.createInvoiceService(invoiceId, serviceId, customer)) {
                                throw new SQLException("Invoice Service Error");
                            }
                        }
                        servicePriceTotal += getServiceTotalPriceByIds(serviceIdList);
                    }
                    Room room = roomDAO.getRoomByID(roomID);
                    Invoice invoice = invoiceDAO.getSimpleInvoiceByID(invoiceId);
                    invoiceDAO.updateInvoiceTotal(invoiceId, Common.calcTotal(invoice.getCheckInDate(), invoice.getCheckOutDate(), room.getPrice()) + servicePriceTotal);

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
        return flag;
    }

    public Double getServiceTotalPriceByIds(List<Integer> serviceIdList) {
        List<Service> services = new ArrayList<>();
        for(Integer id: serviceIdList) {
            Service service = serviceDAO.getServiceByID(id);
            services.add(service);
        }
        return services.stream().mapToDouble(Service::getPrice).sum();
    }


}
