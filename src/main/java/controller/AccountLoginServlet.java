package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.dao.AccountDAO;
import model.dao.CustomerDAO;
import model.entity.Account;
import model.entity.Customer;

import java.io.IOException;

@WebServlet(name = "AccountLoginServlet", value = "/login")
public class AccountLoginServlet extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();
    CustomerDAO customerDAO = new CustomerDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("rememberedUsername")) {
                    request.setAttribute("username", o.getValue());
                }
                if (o.getName().equals("rememberedPassword")) {
                    request.setAttribute("password", o.getValue());
                }
            }

        }
        request.getRequestDispatcher("account-login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        boolean rememberMe = request.getParameter("rememberMe") != null;
        boolean rememberMe = true;
        Account account = accountDAO.login(username, password);
        if (account != null) {
            HttpSession session = request.getSession();
            Customer customer = customerDAO.findByAccountId(account.getAccountId());
            if(customer != null) {
                session.setAttribute("customer", customer);
            }
            session.setAttribute("loggedInUser", account);

            if (rememberMe) {
                // Nếu checkbox "Remember Me" được chọn, tạo và lưu cookies
                Cookie usernameCookie = new Cookie("rememberedUsername", username);
//                Cookie passwordCookie = new Cookie("rememberedPassword", password);

                usernameCookie.setMaxAge(7 * 24 * 60 * 60); // Thời gian sống của cookie (7 ngày)
//                passwordCookie.setMaxAge(7 * 24 * 60 * 60);

                response.addCookie(usernameCookie);
//                response.addCookie(passwordCookie);
            } else {
                // Nếu checkbox không được chọn, xóa cookies nếu có
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("rememberedUsername") || cookie.getName().equals("rememberedPassword")) {
                            cookie.setMaxAge(0); // Đặt thời gian sống của cookie về 0 để xóa
                            response.addCookie(cookie);
                        }
                    }
                }
            }
            response.sendRedirect("home");

        } else {
            request.setAttribute("message", "Username or password is incorrect. Please try again.");
            doGet(request, response);
        }
    }
}
