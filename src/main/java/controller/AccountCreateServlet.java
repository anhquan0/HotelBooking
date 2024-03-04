package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.AccountDAO;
import util.PasswordUtil;
import util.ValidationUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@WebServlet(name = "AccountCreateServlet", value = "/register")
public class AccountCreateServlet extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("account-create.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int abc = 0;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rePassword = request.getParameter("rePassword");
        String email = request.getParameter("email");

        if (!password.equals(rePassword)) {
            request.setAttribute("signUp", "true");
            request.setAttribute("message2", "Password and Re-Password do not match");
            request.getRequestDispatcher("login").forward(request, response);
        } else if (!ValidationUtil.isValidUsername(username)) {
            request.setAttribute("signUp", "true");
            request.setAttribute("message2", "Invalid username");
            request.getRequestDispatcher("login").forward(request, response);
        } else if (!ValidationUtil.isValidPassword(password)) {
            request.setAttribute("signUp", "true");
            request.setAttribute("message2", "Invalid password");
            request.getRequestDispatcher("login").forward(request, response);
        } else if (!ValidationUtil.isValidEmail(email)) {
            request.setAttribute("signUp", "true");
            request.setAttribute("message2", "Invalid email");
            request.getRequestDispatcher("login").forward(request, response);
        } else {
            try {
                String hashPassword = PasswordUtil.generateStrongPasswordHash(password);

                if (accountDAO.existsByUsernameOrEmail(username, email)) {
                    request.setAttribute("signUp", "true");
                    request.setAttribute("message2", "Username or Email already exist.");
                    request.getRequestDispatcher("login").forward(request, response);
                } else {
                    accountDAO.createAccount(username, hashPassword, email);
                    request.setAttribute("message", "Account created successfully");
                    response.sendRedirect("login");
                }
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new ServletException(e);
            }
        }
    }
}
