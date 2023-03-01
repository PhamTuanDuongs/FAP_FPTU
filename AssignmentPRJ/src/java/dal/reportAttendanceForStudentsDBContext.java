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
import model.Room;
import model.TimeSlot;
import model.reportAttendanceForStudents;

/**
 *
 * @author duong
 */
public class reportAttendanceForStudentsDBContext extends DBContext<reportAttendanceForStudents> {

    @Override
    public void insert(reportAttendanceForStudents model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(reportAttendanceForStudents model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(reportAttendanceForStudents model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public reportAttendanceForStudents get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<reportAttendanceForStudents> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<reportAttendanceForStudents> allAttendanceByStidCoid(int studentId, int courseId) {
        ArrayList<reportAttendanceForStudents> attendance = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select l.Date, g.Name,t.SlotID,t.TimeFrom,t.TimeTo,r.rname,r.RoomID,a.[Status],i.instrnumber,a.Comments\n"
                    + "from\n"
                    + "Lecture l join [Group] g\n"
                    + "on l.GroupID = g.GroupID join TimeSlot t \n"
                    + "on l.TimeSlotID = t.SlotID join Course c \n"
                    + "on g.CourseID = c.CourseID join Room r\n"
                    + "on l.RoomID = r.RoomID join StudentGroup sg \n"
                    + "on sg.GroupID = g.GroupID join Student s \n"
                    + "on sg.StudentID = s.StudentID join Attendance a \n"
                    + "on s.StudentID = a.StudentID join Instructor i\n"
                    + "on l.InstructorID = i.InstructorID\n"
                    + "where s.StudentID  = ? and c.CourseID = ?\n"
                    + "group by l.Date, g.Name,t.SlotID,t.TimeFrom,t.TimeTo,c.CourseID , c.Code,r.rname,r.RoomID,a.[Status],i.instrnumber,a.Comments\n"
                    + "ORDER BY l.Date";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                reportAttendanceForStudents attend = new reportAttendanceForStudents();
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("SlotID"));
                t.setTimeFrom(rs.getTime("TimeFrom"));
                t.setTimeTo(rs.getTime("TimeTo"));
                attend.setSlot(t);
                Room r = new Room();
                r.setRoomId(rs.getInt("RoomID"));
                r.setRname(rs.getString("rname"));
                attend.setRoom(r);
                attend.setDate(rs.getDate("Date"));
                attend.setStatus(rs.getString("Status"));
                attend.setComment(rs.getString("Comments"));
                attend.setInstructor(rs.getString("instrnumber"));
                attend.setGroupName(rs.getString("Name"));
                attendance.add(attend);
            }
        } catch (SQLException ex) {
            Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(reportAttendanceForStudentsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return attendance;
    }

    public static void main(String[] args) {
        reportAttendanceForStudentsDBContext e = new reportAttendanceForStudentsDBContext();
        ArrayList<reportAttendanceForStudents> attendance = e.allAttendanceByStidCoid(1, 19);
        System.out.println(attendance.get(0).getComment());
    }
}
