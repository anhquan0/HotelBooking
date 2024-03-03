package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.InvoiceDAO;

@WebServlet(name = "BookingCreateServlet", value = "/create-booking")
public class BookingCreateServlet extends HttpServlet {

    InvoiceDAO invoiceDAO = new InvoiceDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void destroy() {
    }
}