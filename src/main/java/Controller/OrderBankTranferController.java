/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.OrderTableDAO;
import dao.ShoppingCartDAO;
import dto.Customer;
import dto.Discount;
import dto.OrderItem;
import dto.OrderTable;
import dto.ShoppingCart;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Raiku
 */
public class OrderBankTranferController extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        BufferedReader read = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String requestData = read.lines().collect(Collectors.joining());
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(requestData, JsonObject.class);
        Customer c = (Customer) session.getAttribute("user");
        ShoppingCart s = (ShoppingCart) session.getAttribute("shoppingCart");
        if (s.getCartItemList().size() == 0) {
            throw new Error("Error: Shopping Cart empty!");
        } else {
            Discount d = (Discount) session.getAttribute("discount");
            OrderTable o = new OrderTable();
            o.setCustomerId(c);
            if (jsonObject != null && jsonObject.has("shipToAnotherAdr")) {
                boolean shipToAnotherAdr = jsonObject.get("shipToAnotherAdr").getAsBoolean();
                if (shipToAnotherAdr) {
                    String shipAdr = jsonObject.get("shipAdr").getAsString();
                    String shipName = jsonObject.get("shipName").getAsString();
                    String shipPhone = jsonObject.get("shipPhone").getAsString();
                    String shipEmail = jsonObject.get("shipEmail").getAsString();
                    o.setShipAddress(shipAdr);
                    o.setShipMail(shipEmail);
                    o.setShipName(shipName);
                    o.setShipPhone(shipPhone);
                }
            }
            if (o.getShipName()== null && o.getShipAddress() == null && o.getShipPhone() == null && o.getShipMail() == null) {
                o.setShipAddress(c.getAddress());
                o.setShipMail(c.getEmail());
                o.setShipName(c.getCustomerName());
                o.setShipPhone(c.getPhone());
            }
            o.setPaymentMethod("Bank");
            try {
                OrderTable orderTable = new OrderTableDAO().addNewOrder(o, s, d);
                String link = "https://img.vietqr.io/image/TPBank-03869678601-print.png?amount=" + orderTable.getTotalAmount() +"&addInfo=Payment%20Online%20Shopping&accountName=Group%202";
                if (orderTable != null) {
                    session.setAttribute("shoppingCart", new ShoppingCartDAO().removeAllCartItem(s));
                    session.setAttribute("orderDetail", orderTable);
                    session.setAttribute("linkQr", link);
                    response.getWriter().write("success");
                } else {
                    response.getWriter().write("fail");
                }
            } catch (Exception ex) {
                Logger.getLogger(OrderDirectController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
