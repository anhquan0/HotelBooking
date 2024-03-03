package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;

import java.io.IOException;

@WebServlet(name = "RoomUpdateServlet", value = "/update-room")
public class RoomUpdateServlet extends HttpServlet {

    RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("room-update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomID = Integer.parseInt(request.getParameter("roomID"));
        String roomType = request.getParameter("roomType");
        String roomPrice = request.getParameter("roomPrice");
        String roomStatus = request.getParameter("roomStatus");
        String roomDescription = request.getParameter("roomDescription");
        if(roomDAO.updateRoom(roomID, roomType, roomPrice, roomStatus, roomDescription)){
            request.setAttribute("message", "Room updated successfully");
            response.sendRedirect("room-detail?roomID=" + roomID);
        }else{
            request.setAttribute("message", "Room update failed");
            request.getRequestDispatcher("room-update.jsp").forward(request, response);
        }

    }
}
