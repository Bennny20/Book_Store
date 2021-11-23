/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import phientq.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class OrdersDAO implements Serializable {

    public int addOrder(String name, String address)
            throws SQLException, NamingException {
        //1. connect to DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql
                String sql = "Insert into Orders(Name, Address) "
                        + "Output inserted.OrderID "
                        + "Values (?, ?)";

                //3. create statement to load sql
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                stm.setString(2, address);
          
                //4. execute querry
                rs = stm.executeQuery();

                //5. process result
                if (rs.next()) {
                    int orderId = rs.getInt("OrderID");
                    return orderId;
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
        return -1;
    }
    
    public boolean searchName(String name) 
            throws SQLException, NamingException {
        //1. connect DB
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql
                String sql = "Select Name "
                        + "From Orders";
                
                //3. create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, name);
                
                //4. execute stm
                rs = stm.executeQuery();
                
                //5. process result
                if (rs.next()) {
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
}
