/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet(name = "StartupControllerServlet", urlPatterns = {"/StartupControllerServlet"})
public class StartupControllerServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();  
        String url = MyAppConstant.StartUpFeatures.LOGIN_PAGE;
        try {
            //1. Read cookies
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                //2. get last cookies
                for (Cookie cookie : cookies) {
                    //3. get username and password
                    String username = cookie.getName();
                    String password = cookie.getValue(); //decrypt or find hash key
                    //4. Call dao to check login
                    RegisterDAO dao = new RegisterDAO();
                    boolean result = dao.checkLogin(username, password);

                    if (result) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USERNAME", username);
                        String fullName = dao.getFullName(username);
                        session.setAttribute("Fullname", fullName);
                        boolean isRole = dao.checkRole(username);
                        if (isRole) {
                            url = MyAppConstant.StartUpFeatures.SEARCH_PAGE;
                        } else {
                            url = MyAppConstant.StartUpFeatures.SHOW_INFO;
                        }
                    } //end if authentication is ok
                }//end if cookies has existed
            }
        } catch (NamingException ex) {
            log("StartUpServlet Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("StartUpServlet SQL: " + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
            out.close();
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
