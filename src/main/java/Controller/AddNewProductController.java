/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.ProductDAO;
import dto.Product;
import dto.Seller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Raiku
 */
public class AddNewProductController extends HttpServlet {

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
        if (session.getAttribute("userRole") == null || !session.getAttribute("userRole").equals("seller")) {
            response.sendRedirect("404.jsp");
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String readData = reader.lines().collect(Collectors.joining());
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(readData, JsonObject.class);
            String name = jsonObject.get("name").getAsString();
            int category = Integer.parseInt(jsonObject.get("category").getAsString());
            String description = jsonObject.get("description").getAsString();
            int quantity = Integer.parseInt(jsonObject.get("qCurrent").getAsString());
            int quantitySold = Integer.parseInt(jsonObject.get("qSold").getAsString());
            int newPrice = Integer.parseInt(jsonObject.get("nPrice").getAsString());
            int oldPrice = Integer.parseInt(jsonObject.get("oPrice").getAsString());
            String imagePath = jsonObject.get("img").getAsString();
            try {
                Seller seller = (Seller) session.getAttribute("user");
                Product p = new ProductDAO().addNewProduct(name, category, description, quantity, quantitySold, newPrice, oldPrice, imagePath, seller);
                response.getWriter().write("Add new Product Success!!");
            } catch (Exception e) {
                response.getWriter().write(e.getMessage());
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
