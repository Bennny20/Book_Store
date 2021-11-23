/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.register;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import phientq.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class RegisterDAO implements Serializable {

    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        //1. connect to DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select username "
                        + "From Register "
                        + "Where username = ? And password = ?";
                
                //3. create statement to load sql and set value
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);

                //4. execute querry
                rs = stm.executeQuery();

                //5. process result
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    //search dc nhiều dòng
    //list có dc do đọc từ DB lên -> không cung cấp method() set cho bên ngoài truy cập, chỉ dc get
    private List<RegisterDTO> accountList;

    public List<RegisterDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String keyword)
            throws SQLException, NamingException {
        //1. connect to DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Register "
                        + "Where lastname Like ?";

                //3. create statement to load sql and set value
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");

                //4. execute querry
                rs = stm.executeQuery();

                //5. process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    RegisterDTO dto
                            = new RegisterDTO(
                                    username, password, fullName, role);

                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    } //end if allocate memory to account list
                    this.accountList.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean deleteAccount(String username)
        throws SQLException, NamingException {
        //1. connect to DB
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Delete From Register "
                        + "Where username = ?";
                
                //3. create statement to load sql and set value
                stm = con.prepareStatement(sql);
                stm.setString(1, username);

                //4. execute querry
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean Update(String username, String password, boolean role)
            throws SQLException, NamingException {
        //1. connect to DB
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Update Register "
                        + "Set password = ?, "
                        + "isAdmin = ? "
                        + "Where username = ?";
                
                //3. create statement to load sql and set value
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, username);

                //4. execute querry
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean Update(String username, String password)
            throws SQLException, NamingException {
        //1. connect to DB
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Update Register "
                        + "Set password = ? "
                        + "Where username = ?";
                
                //3. create statement to load sql and set value
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, username);

                //4. execute querry
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public String getFullName(String username) 
            throws SQLException, NamingException {
        //1. connect DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql
                String sql = "Select lastname "
                        + "From Register "
                        + "Where username = ?";
                
                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                
                //4. execute RS
                rs = stm.executeQuery();
                
                //5. process result
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    return fullName;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }
    
    public boolean checkSignUp(RegisterDTO dto) 
            throws NamingException, SQLException{
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql string
                String sql = "Insert into Register("
                        + "username, password, lastname, isAdmin"
                        + ") Values(?, ?, ?, ?)";
                
                //3. create statement to load sql and set value
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());

                //4. execute querry
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public boolean checkRole(String username) 
            throws NamingException, SQLException {
        //1.connect DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
             con = DBHelpers.makeConnection();
             if (con != null) {
                 //2. String sql
                 String sql = "Select isAdmin "
                         + "From Register "
                         + "Where username = ?";
                 
                 //3. Create Statement
                 stm = con.prepareStatement(sql);
                 stm.setString(1, username);
                 
                 //4. execute
                 rs = stm.executeQuery();
                 
                 //5. process result
                 if (rs.next()) {
                     boolean isRole = rs.getBoolean("isAdmin");
                     return isRole;
                 }
             }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

} 
