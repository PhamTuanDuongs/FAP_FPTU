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
import model.Lecture;

/**
 *
 * @author duong
 */
public class LectureDBContext extends DBContext<Lecture> {

    @Override
    public void insert(Lecture model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Lecture model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Lecture model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Lecture get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Lecture> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Lecture> allRollNumberDate(String rollNumer, Date from, Date to) {
        ArrayList<Lecture> lecture = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select g.Name,t.SlotID, c.Code,r.rname,a.[Status]\n"
                    + " from \n"
                    + " Lecture l inner join [Group] g \n"
                    + "on l.GroupID = g.GroupID inner join TimeSlot t \n"
                    + " on l.TimeSlotID = t.SlotID inner join Course c\n"
                    + " on g.CourseID = c.CourseID inner join Room r\n"
                    + "  on l.RoomID = r.RoomID inner join StudentGroup sg \n"
                    + " on sg.GroupID = g.GroupID inner join Student s \n"
                    + " on sg.StudentID = s.StudentID  \n"
                    + " inner join Attendance a on s.StudentID = a.StudentID  \n"
                    + " where s.Rollnumber = ? and l.[Date] between ? and ? \n"
                    + "group by s.Rollnumber,l.Date, g.Name,t.SlotID, c.Code,r.rname,a.[Status]";
            stm = connection.prepareStatement(sql);
            stm.setString(1, rollNumer);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                l.setCourse(rs.getString("Code"));
                l.setGroup(rs.getString("Name"));
                l.setRoom(rs.getString("rname"));
                l.setSlotId(rs.getInt("SlotID"));
                l.setStatus(rs.getString("Status"));
                lecture.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecture;
    }

    public static void main(String[] args) {
        LectureDBContext l = new LectureDBContext();
        ArrayList<Lecture> le = l.allRollNumberDate("HE163665", Date.valueOf("2023-03-20"), Date.valueOf("2023-03-24"));
        System.out.println(le.get(0).getCourse());
    }

}
