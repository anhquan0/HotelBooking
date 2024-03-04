package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.CustomerDAO;
import model.dao.InvoiceDAO;
import model.dao.InvoiceServiceDAO;
import model.dao.ServiceDAO;
import model.entity.Account;
import model.entity.Customer;
import model.entity.Service;
import model.service.InvoiceProcessService;

@WebServlet(name = "BookingCreateServlet", value = "/create-booking")
public class BookingCreateServlet extends HttpServlet {



    InvoiceDAO invoiceDAO = new InvoiceDAO();
    ServiceDAO serviceDAO = new ServiceDAO();

    CustomerDAO customerDAO = new CustomerDAO();
    InvoiceServiceDAO invoiceServiceDAO = new InvoiceServiceDAO();
    InvoiceProcessService invoiceProcessService = new InvoiceProcessService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String checkInDateStr = request.getParameter("check_in_date");
        String checkOutDateStr = request.getParameter("check_out_date");
        String roomID = request.getParameter("roomID");

        List<Service> services = serviceDAO.getAllServices();
        request.setAttribute("serviceList", services);
        request.setAttribute("roomID", roomID);

        if (checkInDateStr != null && checkOutDateStr != null) {
            if(!checkInDateStr.isEmpty() && !checkOutDateStr.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime checkInDate = LocalDateTime.parse(checkInDateStr, formatter);
                LocalDateTime checkOutDate = LocalDateTime.parse(checkOutDateStr, formatter);

                request.setAttribute("checkInDate", checkInDate);
                request.setAttribute("checkOutDate", checkOutDate);
            }
        }

        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkInDateStr = request.getParameter("check_in_time");
        String checkOutDateStr = request.getParameter("check_out_time");
        if (checkInDateStr != null && checkOutDateStr != null) {
            if (!checkInDateStr.isEmpty() && !checkOutDateStr.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime checkInTime = LocalDateTime.parse(checkInDateStr, formatter);
                LocalDateTime checkOutTime = LocalDateTime.parse(checkOutDateStr, formatter);

                String[] serviceIdStrArr = request.getParameterValues("services");

                Integer roomID = Integer.parseInt(request.getParameter("roomID"));
                String paymentMethod = request.getParameter("payments");

                String note = request.getParameter("note");

                HttpSession session = request.getSession();
                Customer customer = (Customer) session.getAttribute("customer");
                invoiceProcessService.createInvoice(checkInTime, checkOutTime, serviceIdStrArr, roomID, paymentMethod, note, customer);
            }
        }
        response.sendRedirect("/room-list.jsp?statusCode=500");
    }

    public void destroy() {
    }
}