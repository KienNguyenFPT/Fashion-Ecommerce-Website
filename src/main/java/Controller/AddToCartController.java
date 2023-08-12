/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.ShoppingCartDAO;
import dto.Customer;
import dto.ShoppingCart;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class AddToCartController extends HttpServlet {

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
        String userRole = (String) request.getSession().getAttribute("userRole");
        HttpSession session = request.getSession();
        if (userRole != null && userRole.equals("customer")) {
            try {
                BufferedReader read = new BufferedReader(new InputStreamReader(request.getInputStream()));
                String requestData = read.lines().collect(Collectors.joining());
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(requestData, JsonObject.class);
                int pID = jsonObject.get("pID").getAsInt();
                Customer cus = (Customer) session.getAttribute("user");
                ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                ShoppingCart temp = new ShoppingCartDAO().addToCart(pID, cart);
                if (temp != null) {
                    session.setAttribute("shoppingCart", temp);
                    response.getWriter().write("success");
                }
            } catch (Exception e) {
                response.getWriter().write(e.getMessage());
            }
        } else {
            response.getWriter().write("Must Login!");
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
