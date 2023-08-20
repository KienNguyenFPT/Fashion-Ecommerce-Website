/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import dao.ChatroomDAO;
import dao.MessageDAO;
import dto.Customer;
import dto.Message;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raiku
 */
public class openChatRoomController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String room = request.getParameter("chat");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("userRole");
        int roomId = 0;
        if ((room.equals("createNew") && role.equals("admin")) || role == null){
            response.sendRedirect("404.jsp");
        } else if(room.equals("createNew") && role.equals("customer")){
            Customer c = (Customer) session.getAttribute("user");
            roomId = new ChatroomDAO().createNewRoom(c).getRoomId();
            session.setAttribute("chatRoom", roomId);
            response.sendRedirect("chatBox.jsp");
        } else if (room != null && room.length() > 0){
            roomId = Integer.parseInt(room);
            session.setAttribute("chatRoom", roomId);
            List<Message> chatList = new MessageDAO().getChatListfromRoomId(roomId);
            session.setAttribute("chatList", chatList);
            response.sendRedirect("chatBox.jsp");
        }else{
            request.getRequestDispatcher("404.jsp").forward(request, response);
        }
        if (roomId > 0){
            session.setAttribute("room", new ChatroomDAO().getChatroomById(roomId));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
