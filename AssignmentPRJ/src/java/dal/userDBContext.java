/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author sonnt
 */
public class userDBContext extends DBContext<User> {

    public User get(String username, String password) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT [username]\n"
                + "      ,[password]\n"
                + "      ,[studentId]\n"
                + "      ,[instructorId]\n"
                + "      ,[roleId]\n"
                + "      ,[display]\n"
                + "  FROM [User]\n"
                + "  WHERE [username] = '" + username + "'\n"
                + "  AND [password] = '" + password + "'";
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                User s = new User();
                s.setUsername(rs.getString("username"));
                s.setRoleId(rs.getInt("roleId"));
                s.setStudentId(rs.getInt("studentId"));
                s.setInstructorId(rs.getInt("instructorId"));
                s.setDisplayname(rs.getString("display"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(userDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(userDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(userDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(userDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;

    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public User get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void main(String[] args) {
        userDBContext s = new userDBContext();
        User st = s.get("duongbeu777@gmail.com", "duong12345");
        System.out.println(st.getInstructorId());
    }
}
