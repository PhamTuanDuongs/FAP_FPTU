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
            String sql = "select l.date, g.Name,c.Code,r.RoomID,r.rname,a.Status,a.comment,l.TimeSlotID,s.TimeFrom,s.TimeTo,i.instrnumber from Lecture l inner join Attendance a\n"
                    + "on l.LectureID  = a.LectureID inner join [Group] g \n"
                    + "on l.GroupID = g.GroupID join Course c on \n"
                    + "g.CourseID = c.CourseID join Room r\n"
                    + "on l.RoomID = r.RoomID join TimeSlot s\n"
                    + "on l.TimeSlotID = s.SlotID join Instructor i \n"
                    + "on i.InstructorID = l.InstructorID\n"
                    + "where a.StudentID = ? and c.CourseID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                reportAttendanceForStudents attend = new reportAttendanceForStudents();
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("TimeSlotID"));
                t.setTimeFrom(rs.getTime("TimeFrom"));
                t.setTimeTo(rs.getTime("TimeTo"));
                attend.setSlot(t);
                Room r = new Room();
                r.setRoomId(rs.getInt("RoomID"));
                r.setRname(rs.getString("rname"));
                attend.setRoom(r);
                attend.setDate(rs.getDate("date"));
                attend.setStatus(rs.getString("Status"));
                attend.setComment(rs.getString("comment"));
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
        System.out.println(attendance.get(0).getGroupName());
    }
}
