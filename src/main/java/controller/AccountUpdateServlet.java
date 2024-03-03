package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.AccountDAO;

import java.io.IOException;

@WebServlet(name = "AccountUpdateServlet", value = "/account-update")
public class AccountUpdateServlet extends HttpServlet {

    AccountDAO accountDAO = new AccountDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie arr[] = request.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("rememberedUsername")) {
                    request.setAttribute("username", o.getValue());
                    break;
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
