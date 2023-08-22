/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import dao.OrderTableDAO;
import dto.OrderTable;
import model.OrderTableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.OrderResponse;

/**
 *
 * @author Raiku
 */
public class OrderManagerSortFilterController extends HttpServlet {

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
        try {
            HttpSession session = request.getSession();
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String readData = reader.lines().collect(Collectors.joining());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(readData, JsonObject.class);
            String method = jsonObject.get("method").getAsString();
            String status = jsonObject.get("status").getAsString();
            int offset = jsonObject.get("offset").getAsInt();
            long total = 0;
            if ((method != null && method.length() > 0) || (status != null && status.length() > 0)) {
                List<OrderTable> orderList;
                if (method.equals("all")) {
                    if (status.equals("all")) {
                        orderList = new OrderTableDAO().loadOrderTable(offset);
                        total = new OrderTableDAO().loadTotalOrder();
                    } else {
                        orderList = new OrderTableDAO().loadOrderByStatus(status, offset);
                        total = new OrderTableDAO().loadTotalOrderByStatus(status);
                    }
                } else {
                    if (status.equals("all")) {
                        orderList = new OrderTableDAO().loadOrderByMethod(method, offset);
                        total = new OrderTableDAO().loadTotalOrderByMethod(method);
                    } else {
                        orderList = new OrderTableDAO().loadOrderByStatusAndMethod(method, status, offset);
                        total = new OrderTableDAO().loadTotalOrderByMethodAndStatus(method, status);
                    }
                }
                List<OrderTableModel> orderResult = new ArrayList<OrderTableModel>();
                for (OrderTable o : orderList) {
                    OrderTableModel oTemp = new OrderTableModel(o.getOrderId(), o.getOrderDate(), o.getTotalAmount(), o.getPaymentMethod(), o.getStatus(), o.getCustomerId().getCustomerId());
                    orderResult.add(oTemp);
                }
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(gson.toJson(new OrderResponse(orderResult, total)));
            }
        } catch (Exception e) {
            throw new Error("Now, error is occurring. Please try again!");
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
