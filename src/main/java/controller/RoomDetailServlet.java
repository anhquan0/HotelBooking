package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.RoomDAO;

import java.io.IOException;

@WebServlet(name = "RoomDetailServlet", value = "/room-detail")
public class RoomDetailServlet extends HttpServlet {
    RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        request.setAttribute("room", roomDAO.getRoomByID(roomID));
        request.getRequestDispatcher("room-detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
