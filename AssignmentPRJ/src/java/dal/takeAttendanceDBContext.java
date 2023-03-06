/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.takeAttendance;

/**
 *
 * @author duong
 */
public class takeAttendanceDBContext extends DBContext<takeAttendance> {

    @Override
    public void insert(takeAttendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(takeAttendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(takeAttendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public takeAttendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<takeAttendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<takeAttendance> allStudentsBySlotGroupId(Date date, int groupid, int instructorid, int slot, int lectureid) {
        ArrayList<takeAttendance> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Name,l.TimeSlotID,l.LectureID from \n"
                    + "Attendance a inner join Student s\n"
                    + "on a.StudentID = s.StudentID inner join Lecture l \n"
                    + "on a.LectureID = l.LectureID inner join [Group] g\n"
                    + "on l.GroupID = g.GroupID\n"
                    + "where  l.Date = ? and g.GroupID = ? and l.InstructorID = ? and l.TimeSlotID = ? and l.LectureID = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, groupid);
            stm.setInt(3, instructorid);
            stm.setInt(4, slot);
            stm.setInt(5, lectureid);
            rs = stm.executeQuery();
            while (rs.next()) {
                takeAttendance t = new takeAttendance();
                t.setStudentId(rs.getInt("StudentID"));
                t.setFirstName(rs.getString("Firstname"));
                t.setLastName(rs.getString("Lastname"));
                t.setRollnumber(rs.getString("Rollnumber"));
                t.setGroupId(rs.getInt("GroupID"));
                t.setGroupName(rs.getString("Name"));
                t.setSlotid(rs.getInt("TimeSlotID"));
                t.setLectureid(rs.getInt("LectureID"));
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        takeAttendanceDBContext t =  new takeAttendanceDBContext();
        ArrayList<takeAttendance> list = t.allStudentsBySlotGroupId(Date.valueOf("2023-03-20"), 1, 6, 1, 1);
        System.out.println(list.size());
    }

}
