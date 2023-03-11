/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Group;
import model.Session;
import model.Student;
import model.TimeSlot;
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

    public ArrayList<Attendance> allStudentsBySlotGroupId(Date date, int groupid, int instructorid, int slot, int lectureid) {
        ArrayList<Attendance> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Gname,l.TimeSlotID,l.SessionId from \n"
                    + "                    Attendance a inner join Student s \n"
                    + "                     on a.StudentID = s.StudentID inner join Session l  \n"
                    + "                    on a.LectureID = l.SessionId inner join [Group] g \n"
                    + "                     on l.GroupID = g.GroupID \n"
                    + "                     where  l.Date = ? and g.GroupID = ? and l.InstructorID = ? and l.TimeSlotID = ? and l.SessionId = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, groupid);
            stm.setInt(3, instructorid);
            stm.setInt(4, slot);
            stm.setInt(5, lectureid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                Student s = new Student();
                s.setStudentid(rs.getInt("StudentID"));
                s.setFirstName(rs.getString("Firstname"));
                s.setLastName(rs.getString("Lastname"));
                s.setRollnumber(rs.getString("Rollnumber"));
                a.setStudent(s);
                Session ss = new Session();
                ss.setId(rs.getInt("Sessionid"));
                Group g = new Group();

                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("Gname"));
                ss.setGroup(g);
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("TimeSlotID"));
                ss.setSlot(t);
                a.setSession(ss);
                list.add(a);
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

    public void takeAttendance(ArrayList<Attendance> attend) {
        PreparedStatement stm = null;

        try {
            String sql = "update Attendance SET Status = ?, comment = ?, Record = ? from \n"
                    + "Attendance a inner join Student s\n"
                    + "on a.StudentID = s.StudentID inner join Lecture l \n"
                    + "on a.LectureID = l.LectureID inner join [Group] g\n"
                    + "on l.GroupID = g.GroupID\n"
                    + "where l.LectureID = ? and s.StudentID = ? and  l.Date = ? and g.GroupID = ? and l.InstructorID = ? and l.TimeSlotID = ?";
            stm = connection.prepareStatement(sql);
            for (Attendance attendance : attend) {
                stm.setString(1, attendance.getStatus());
                stm.setString(2, attendance.getComment());
                stm.setTimestamp(3, attendance.getRecordTime());
                stm.setInt(4, attendance.getSession().getId());
                stm.setInt(5, attendance.getStudent().getStudentid());
//                LocalDate currentdate = LocalDate.now();
                stm.setDate(6, Date.valueOf("2023-03-20"));
                stm.setInt(7, attendance.getSession().getGroup().getGroupId());
                stm.setInt(8, attendance.getSession().getInstructor().getInstructorId());
                stm.setInt(9, attendance.getSession().getSlot().getSlotId());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
    }

    public ArrayList<Attendance> viewAttendance(Date date, int groupid, int instructorid, int slot, int lectureid) {
        ArrayList<Attendance> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Gname,l.TimeSlotID,l.LectureID,a.comment,a.Status from \n"
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
//                Attendance t = new Attendance();
//                t.setStudentId(rs.getInt("StudentID"));
//                t.setFirstName(rs.getString("Firstname"));
//                t.setLastName(rs.getString("Lastname"));
//                t.setRollnumber(rs.getString("Rollnumber"));
//                t.setGroupId(rs.getInt("GroupID"));
//                t.setGroupName(rs.getString("Gname"));
//                t.setSlotid(rs.getInt("TimeSlotID"));
//                t.setLectureid(rs.getInt("LectureID"));
//                t.setComment(rs.getString("comment"));
//                t.setStatus(rs.getString("Status"));
//                list.add(t);
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
        takeAttendanceDBContext t = new takeAttendanceDBContext();
        ArrayList<Attendance> list = t.allStudentsBySlotGroupId(Date.valueOf("2023-03-20"), 1, 6, 1, 1);
        System.out.println(list.get(1).getSession().getId());
    }

}
