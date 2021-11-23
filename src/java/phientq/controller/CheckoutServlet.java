/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phientq.cart.CartObject;
import phientq.orderdetails.OrderDetailsDAO;
import phientq.orders.OrdersDAO;
import phientq.utils.MyAppConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/CheckoutServlet"})
public class CheckoutServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");
        String url = MyAppConstant.CheckOutFeatures.SHOPPING_BOOK;

        try {
            //1. cust go to cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. cust takes cart
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        OrdersDAO dao = new OrdersDAO();
                        int result = dao.addOrder(name, address);
                        if (result > 0) {
                            for (String title : items.keySet()) {
                                OrderDetailsDAO dao1 = new OrderDetailsDAO();
                                boolean result1 = dao1.addOrderDetails(title, items.get(title), result);
                                if (result1) {
                                    session.removeAttribute("CART");
                                }
                            }
                            url = MyAppConstant.CheckOutFeatures.SHOW_BOOK_PROCESS;
                        }
                    }
                }
            }
        } catch (NamingException ex) {
            log("CheckoutServlet    Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("CheckoutServlet    SQL: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
