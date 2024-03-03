package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "AccountLogoutServlet", value = "/logout")
public class AccountLogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xóa thông tin đăng nhập khỏi session khi người dùng đăng xuất
        HttpSession session = request.getSession();
        session.removeAttribute("loggedInUser");

        // Xóa cookie "rememberedPassword" để loại bỏ mật khẩu ghi nhớ
        Cookie rememberedPasswordCookie = new Cookie("rememberedPassword", null);
        rememberedPasswordCookie.setMaxAge(0);
        response.addCookie(rememberedPasswordCookie);

        response.sendRedirect("login");
    }
}