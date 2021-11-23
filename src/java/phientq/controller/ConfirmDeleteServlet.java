/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phientq.register.RegisterDAO;
import phientq.utils.MyAppConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ConfirmDeleteServlet", urlPatterns = {"/ConfirmDeleteServlet"})
public class ConfirmDeleteServlet extends HttpServlet {

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

        String url = MyAppConstant.ConfirmDeleteFeatures.SEARCH_PAGE;
        HttpSession session = request.getSession(false);
        String action = request.getParameter("btAction");
        try {
            if (session == null) {
                url = MyAppConstant.ConfirmDeleteFeatures.LOGIN_PAGE;
            } else {
                if (action.equals("Yes")) {
                    RegisterDAO dao = new RegisterDAO();
                    boolean result = dao.deleteAccount((String) session.getAttribute("USER"));
                    if (result) {
                        url = "SearchProcess?"
                                + "txtSearchValue=" + (String) session.getAttribute("LASTSEARCHVALUE");
                    }
                } else if (action.equals("No")) {
                    url = "SearchProcess?"
                            + "txtSearchValue=" + (String) session.getAttribute("LASTSEARCHVALUE");
                }
            }
        } catch (NamingException ex) {
            log("ConfirmDeleteServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("ConfirmDeleteServlet_SQL: " + ex.getMessage());
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
