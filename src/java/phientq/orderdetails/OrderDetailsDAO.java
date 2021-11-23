/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.orderdetails;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import phientq.utils.DBHelpers;

/**
 *
 * @author Admin
 */
public class OrderDetailsDAO implements Serializable {

    public boolean addOrderDetails(String BookID, int quantity, int OrderID)
            throws SQLException, NamingException {
        //connect DB
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create sql
                String sql = "Insert into OrderDetails(Book_ID, Quantity, OrderID) "
                        + "Values (?, ?, ?)";

                //3. create stm
                stm = con.prepareStatement(sql);
                stm.setString(1, BookID);
                stm.setInt(2, quantity);
                stm.setInt(3, OrderID);

                //4. execute
                int affectedRow = stm.executeUpdate();
                
                //5. Process result
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

}
