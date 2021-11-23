/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phientq.book;

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
public class BookDAO implements Serializable{

    public List<BookDTO> getBookList() 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. create SQL
                String sql = "Select ID, name "
                        + "From Book";
                
                //3. create statement
                stm = con.prepareStatement(sql);
                
                //4. execute value
                rs = stm.executeQuery();
                List<BookDTO>list = new ArrayList<>();
                
                //5. process result
                while (rs.next()) {
                    BookDTO dto = new BookDTO(rs.getString(1), rs.getString(2));
                    list.add(dto);
                }
                return list;
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
    
}
    