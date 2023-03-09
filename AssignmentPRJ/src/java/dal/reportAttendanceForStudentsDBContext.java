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
import model.Attendance;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
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

    public ArrayList<Attendance> allAttendanceByStidCoid(int studentId, int courseId) {
        ArrayList<Attendance> attendance = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
          String sql = "select l.date,l.SessionID, g.Gname,g.GroupID,c.CourseID,c.Code,C.Cname,r.RoomID,r.rname,a.Status,a.comment,a.Record,l.TimeSlotID,s.TimeFrom,s.TimeTo,i.InstructorID,i.instrnumber\n"
                    + "			from Session l inner join Attendance a \n"
                    + "                     on l.SessionID  = a.LectureID inner join [Group] g  \n"
                    + "                    on l.GroupID = g.GroupID join Course c on \n"
                    + "                     g.CourseID = c.CourseID join Room r \n"
                    + "                    on l.RoomID = r.RoomID join TimeSlot s \n"
                    + "                    on l.TimeSlotID = s.SlotID join Instructor i  \n"
                    + "                     on i.InstructorID = l.InstructorID \n"
                    + "                     where a.StudentID = ? and c.CourseID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, studentId);
            stm.setInt(2, courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setStatus(rs.getString("Status"));
                a.setComment(rs.getString("comment"));
                a.setRecordTime(rs.getTimestamp("Record"));

                Session s = new Session();
                s.setDate(rs.getDate("Date"));
                s.setId(rs.getInt("SessionID"));

                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("TimeSlotID"));
                t.setTimeFrom(rs.getTime("TimeFrom"));
                t.setTimeTo(rs.getTime("TimeTo"));
                s.setSlot(t);

                Group g = new Group();
                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("Gname"));

                Instructor i = new Instructor();
                i.setInstructorId(rs.getInt("InstructorID"));
                i.setinstrnumber(rs.getString("instrnumber"));

                s.setInstructor(i);
                Course c = new Course();
                c.setCode(rs.getString("Code"));
                c.setName(rs.getString("Cname"));
                c.setCourseId(rs.getInt("CourseID"));
                g.setCourse(c);
                s.setGroup(g);

                Room r = new Room();
                r.setRoomId(rs.getInt("RoomID"));
                r.setRname(rs.getString("rname"));
                s.setRoom(r);

                a.setSession(s);
                attendance.add(a);
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
        ArrayList<Attendance> attendance = e.allAttendanceByStidCoid(1, 19);
        System.out.println(attendance.get(0).getSession().getRoom().getRname());
    }
}
