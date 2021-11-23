/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phientq.register.RegisterDAO;
import phientq.register.RegisterUpdateError;
import phientq.utils.MyAppConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        String searchValue = request.getParameter("lastSearchValue");

        RegisterUpdateError errors = new RegisterUpdateError();

        boolean role = false;
        boolean foundErr = false;

        ServletContext context = this.getServletContext();
        Properties properties = (Properties) context.getAttribute("SITE_MAP");
        String url = properties.getProperty(MyAppConstant.UpdateFeatures.ERROR_PAGE);

        HttpSession session = request.getSession(false);
        try {
            if (session == null) {
                url = properties.getProperty(MyAppConstant.UpdateFeatures.LOGIN_PAGE);
                return;
            }
            
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthViolent("Password requires from 6 to 20 chars");
            } else if (!password.trim().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,20}$")) {
                foundErr = true;
                errors.setPasswordInvalidate("Password requires at lease one digit, "
                        + "at least one lowercase character, "
                        + "at least one uppercase character, "
                        + "and not allow space!");
            }
            
            if (foundErr) {
                request.setAttribute("UPDATEERRORS", errors);
                url = properties.getProperty(MyAppConstant.UpdateFeatures.SEARCH_CONTROLLER)
                        + "?txtSearchValue=" + searchValue;
                return;
            }
            if (username.equals((String) session.getAttribute("USERNAME"))) {
                RegisterDAO dao = new RegisterDAO();
                boolean result = dao.Update(username, password);
                if (result) {
                    url = properties.getProperty(MyAppConstant.UpdateFeatures.SEARCH_CONTROLLER)
                            + "?txtSearchValue=" + searchValue;
                }
            } else {
                if (admin != null) {
                    role = true;
                }
                RegisterDAO dao = new RegisterDAO();
                boolean result = dao.Update(username, password, role);
                if (result) {
                    url = properties.getProperty(MyAppConstant.UpdateFeatures.SEARCH_CONTROLLER)
                            + "?txtSearchValue=" + searchValue;
                }
            }

        } catch (SQLException ex) {
            log("Update    SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            log("Update    Naming" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
