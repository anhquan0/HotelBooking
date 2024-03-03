package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;

import java.io.IOException;

@WebServlet(name = "RoomCreateServlet", value = "/create-room")
public class RoomCreateServlet extends HttpServlet {
    RoomDAO roomDAO = new RoomDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("room-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomType = request.getParameter("roomType");
        String roomPrice = request.getParameter("roomPrice");
        String roomStatus = request.getParameter("roomStatus");
        String roomDescription = request.getParameter("roomDescription");
        if(roomDAO.createRoom(roomType, roomPrice, roomStatus, roomDescription)){
            request.setAttribute("message", "Room created successfully");
            response.sendRedirect("room-list");
        }else{
            request.setAttribute("message", "Room creation failed");
            request.getRequestDispatcher("room-create.jsp").forward(request, response);
        }

    }
}
