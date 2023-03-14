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
import model.Group;
import model.Student;

/**
 *
 * @author duong
 */
public class listStudentDBContext extends DBContext<Student> {

    @Override
    public void insert(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Student get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Student> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Student> studentByGroupID(int groupid) {
        ArrayList<Student> student = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Gname FROM  \n"
                    + "                        Student s LEFT JOIN [StudentGroup] sg ON s.StudentID = sg.StudentID \n"
                    + "                          LEFT JOIN [Group] g ON g.GroupID = sg.GroupID \n"
                    + "						  where g.GroupID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Student st = new Student();
                st.setStudentid(rs.getInt("StudentID"));
                st.setFirstName(rs.getString("Firstname"));
                st.setLastName(rs.getString("Lastname"));
                st.setRollnumber(rs.getString("Rollnumber"));
                Group g = new Group();
                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("Gname"));
                st.setGroup(g);
                student.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(listStudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(listStudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(listStudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(listStudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return student;
    }

    public static void main(String[] args) {
        listStudentDBContext list = new listStudentDBContext();
         ArrayList<Student> student = list.studentByGroupID(1);
         System.out.println(student.size());
    }
}
