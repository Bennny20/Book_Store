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
import phientq.register.RegisterCreateError;
import phientq.register.RegisterDAO;
import phientq.register.RegisterDTO;
import phientq.utils.MyAppConstant;

/**
 *
 * @author Admin
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {
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
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        RegisterCreateError errors = new RegisterCreateError();
        //tạo 1 biến để kiểm tra lỗi
        boolean foundErr = false;
        
        ServletContext context = this.getServletContext();
        Properties properties = (Properties)context.getAttribute("SITE_MAP");
        String url = properties.getProperty(MyAppConstant.SignUpFeatures.ERROR_PAGE);
        try {
            //1. Kiểm tra lỗi
            if (username.trim().length() < 6 || username.trim().length() > 30) {
                foundErr = true;
                errors.setFullNameLengthViolent("Username requires from 6 to 30 chars");
            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundErr = true;
                errors.setPasswordLengthViolent("Password requires from 6 to 20 chars");
            } else if (!password.trim().equals(confirm.trim())) {
                foundErr = true;
                errors.setConfirmNotMatch("Confirm must match password!!");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setFullNameLengthViolent("Full name requires from 2 to 50 chars");
            }
            //1.1 nếu có thì quăng ra cho ng dùng sửa
            if (foundErr) {
                request.setAttribute("SIGNUPERRORS", errors);
                return;
            } else {
                //1.2 nếu không gọi DAO, insert cái mới nhập vào DB
                RegisterDAO dao = new RegisterDAO();
                RegisterDTO dto = new RegisterDTO(username, password, fullName, false);
                boolean result = dao.checkSignUp(dto);
                //2. If task is success, redirect to Login Page
                if (result) {
                    url = properties.getProperty(MyAppConstant.SignUpFeatures.LOGIN_PAGE);
                }
            }

        } catch (NamingException ex) {
            log("SignUpServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("SignUpServlet_SQL: " + msg);
            if (msg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("SIGNUPERRORS", errors);
            }
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
