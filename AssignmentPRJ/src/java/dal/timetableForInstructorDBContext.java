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
public class timetableForInstructorDBContext extends DBContext<Lecture> {

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

    public ArrayList<Lecture> allSlotInWeek(int instructorId, Date from, Date to) {
        ArrayList<Lecture> lecture = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select g.Name,c.Code,r.rname,a.Status,l.TimeSlotID, DATEPART(WEEKDAY,Date) as WeekDay from Lecture l inner join Attendance a\n"
                    + "on l.LectureID  = a.LectureID inner join Instructor i on i.InstructorId = l.InstructorID\n"
                    + "inner join [Group] g \n"
                    + "on l.GroupID = g.GroupID join Course c on \n"
                    + "g.CourseID = c.CourseID join Room r\n"
                    + "on l.RoomID = r.RoomID join TimeSlot s \n"
                    + "on l.TimeSlotID = s.SlotID\n"
                    + "where l.InstructorID = ? and l.[Date] between ? and ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, instructorId);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                l.setGroupName(rs.getString("Name"));
                l.setCourse(rs.getString("Code"));
                l.setRname(rs.getString("rname"));
                l.setStatus(rs.getString("Status"));
                l.setSlot(rs.getInt("TimeSlotID"));
                l.setWeekDay(rs.getInt("WeekDay"));
                lecture.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(timetableForInstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(timetableForInstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(timetableForInstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(timetableForInstructorDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecture;
    }
    
    public static void main(String[] args) {
        timetableForInstructorDBContext t = new timetableForInstructorDBContext();
        ArrayList<Lecture> lecture = t.allSlotInWeek(6, Date.valueOf("2023-03-20"), Date.valueOf("2023-03-24"));
        System.out.println(lecture.get(0).getSlot());
    }
}
