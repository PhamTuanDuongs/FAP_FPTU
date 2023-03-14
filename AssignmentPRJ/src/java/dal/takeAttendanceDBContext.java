/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Attendance;
import model.Course;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.Student;
import model.TimeSlot;

/**
 *
 * @author duong
 */
public class takeAttendanceDBContext extends DBContext<Attendance> {

    @Override
    public void insert(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Attendance model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Attendance get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Attendance> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Attendance> allStudentsBySlotGroupId(Date date, int groupid, int instructorid, int slot, int sessionid) {
        ArrayList<Attendance> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Gname,ses.TimeSlotID,ses.SessionId FROM  \n"
                    + "                   Student s LEFT JOIN [StudentGroup] sg ON s.StudentID = sg.StudentID\n"
                    + "                   LEFT JOIN [Group] g ON g.GroupID = sg.GroupID left join Course c  \n"
                    + "                   on c.CourseID = g.CourseID LEFT JOIN [Session] ses ON ses.GroupID = g.GroupID \n"
                    + "                   LEFT JOIN [Attendance] a ON ses.sessionid = a.SessionID AND s.StudentID = a.StudentID  left join Instructor i on ses.InstructorID = i.InstructorID\n"
                    + "                   where ses.Date = ? and g.GroupID = ?  and i.InstructorID = ? and ses.TimeSlotID = ? and ses.SessionID = ? \n"
                    + "		          order by s.StudentID";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, groupid);
            stm.setInt(3, instructorid);
            stm.setInt(4, slot);
            stm.setInt(5, sessionid);
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

    public void takeAttendance(ArrayList<Attendance> attend, int sessionid) {
        try {
            connection.setAutoCommit(false);
            String sql_update_session = "UPDATE  [Session]\n"
                    + "   SET [SessionStatus] = 'attended'\n"
                    + " WHERE SessionId = ?";
            PreparedStatement stm_update_session = connection.prepareStatement(sql_update_session);
            stm_update_session.setInt(1, sessionid);
            stm_update_session.executeUpdate();
            for (Attendance attendance : attend) {
                String sql_insert_att = "INSERT INTO  [Attendance] \n"
                        + "                     ([StudentID] \n"
                        + "                     ,[SessionID]  \n"
                        + "                    ,[Status] \n"
                        + "                    ,[Record] \n"
                        + "                     ,[comment]) \n"
                        + "                     VALUES(?,?,?,?,?)";
                PreparedStatement stm_insert_att = connection.prepareStatement(sql_insert_att);
                stm_insert_att.setInt(1, attendance.getStudent().getStudentid());
                stm_insert_att.setInt(2, attendance.getSession().getId());
                stm_insert_att.setString(3, attendance.getStatus());
                stm_insert_att.setTimestamp(4, attendance.getRecordTime());
                stm_insert_att.setString(5, attendance.getComment());
                stm_insert_att.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
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
            String sql = " SELECT s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Gname,ses.TimeSlotID,ses.SessionId,a.Status,a.comment,a.Record FROM  \n"
                    + "                    Student s LEFT JOIN [StudentGroup] sg ON s.StudentID = sg.StudentID\n"
                    + "                   LEFT JOIN [Group] g ON g.GroupID = sg.GroupID left join Course c  \n"
                    + "                   on c.CourseID = g.CourseID LEFT JOIN [Session] ses ON ses.GroupID = g.GroupID \n"
                    + "                  LEFT JOIN [Attendance] a ON ses.sessionid = a.SessionID AND s.StudentID = a.StudentID  left join Instructor i on ses.InstructorID = i.InstructorID\n"
                    + "                   where ses.Date = ? and g.GroupID = ?  and i.InstructorID = ? and ses.TimeSlotID = ? and ses.SessionID = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, groupid);
            stm.setInt(3, instructorid);
            stm.setInt(4, slot);
            stm.setInt(5, lectureid);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance t = new Attendance();
                t.setStatus(rs.getString("Status"));
                t.setComment(rs.getString("comment"));
                t.setRecordTime(rs.getTimestamp("Record"));
                Student st = new Student();
                st.setStudentid(rs.getInt("StudentID"));
                st.setFirstName(rs.getString("Firstname"));
                st.setLastName(rs.getString("Lastname"));
                st.setRollnumber(rs.getString("Rollnumber"));
                t.setStudent(st);
                Session s = new Session();
                s.setId(rs.getInt("Sessionid"));

                TimeSlot sl = new TimeSlot();
                sl.setSlotId(rs.getInt("TimeSlotID"));
                s.setSlot(sl);
                Group g = new Group();
                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("Gname"));
                s.setGroup(g);
                t.setSession(s);
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

    public void updateAttendance(ArrayList<Attendance> attend) {

        try {
            String sql = "UPDATE [Attendance]\n"
                    + "   SET  [Status] = ?\n"
                    + "      ,[Record] = ?\n"
                    + "      ,[comment] = ?\n"
                    + " WHERE StudentID = ? and SessionID = ?";
            PreparedStatement stm_update_att = connection.prepareStatement(sql);
            for (Attendance attendance : attend) {
                stm_update_att.setString(1, attendance.getStatus());
                stm_update_att.setTimestamp(2, attendance.getRecordTime());
                stm_update_att.setString(3, attendance.getComment());
                stm_update_att.setInt(4, attendance.getStudent().getStudentid());
                stm_update_att.setInt(5, attendance.getSession().getId());
                stm_update_att.executeUpdate();
                stm_update_att.executeUpdate();
            }
        } catch (SQLException ex) {

            Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(takeAttendanceDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList<Attendance> statusStudents(int groupId) {
        ArrayList<Attendance> attendance = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT s.StudentID,s.Firstname,s.Lastname,s.Rollnumber,g.GroupID,g.Gname,ses.TimeSlotID,ses.SessionId,a.Status FROM  \n"
                    + "                        Student s LEFT JOIN [StudentGroup] sg ON s.StudentID = sg.StudentID \n"
                    + "                          LEFT JOIN [Group] g ON g.GroupID = sg.GroupID left join Course c   \n"
                    + "                              on c.CourseID = g.CourseID LEFT JOIN [Session] ses ON ses.GroupID = g.GroupID  \n"
                    + "                           LEFT JOIN [Attendance] a ON ses.sessionid = a.SessionID AND s.StudentID = a.StudentID  left join Instructor i on ses.InstructorID = i.InstructorID \n"
                    + "                            where g.GroupID = ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, groupId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Attendance a = new Attendance();
                a.setStatus(rs.getString("Status"));

                Session s = new Session();
                s.setId(rs.getInt("SessionID"));

                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("TimeSlotID"));
                s.setSlot(t);

                Group g = new Group();
                g.setGroupId(rs.getInt("GroupID"));
                Student st = new Student();
                st.setStudentid(rs.getInt("StudentID"));
                st.setFirstName(rs.getString("Firstname"));
                st.setLastName(rs.getString("Lastname"));
                st.setRollnumber(rs.getString("Rollnumber"));
                a.setStudent(st);
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

        takeAttendanceDBContext t = new takeAttendanceDBContext();
        ArrayList<Attendance> aa = t.statusStudents(1);
//        Attendance a = new Attendance();
//        Student st = new Student();
//        st.setStudentid(1);
//        a.setStudent(st);
//        Group g = new Group();
//        g.setGroupId(1);
//        Session ss = new Session();
//        ss.setId(1);
//        ss.setGroup(g);
//        TimeSlot slot = new TimeSlot();
//        slot.setSlotId(1);
//        ss.setSlot(slot);
//        Instructor i = new Instructor();
//        i.setInstructorId(6);
//        ss.setInstructor(i);
//        a.setSession(ss);
//
//        a.setComment("h");
//
//        a.setStatus("prese");
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        a.setRecordTime(timestamp);
//        aa.add(a);
//        t.updateAttendance(aa);
        System.out.println(aa.get(0).getStudent().getFirstName());
    }

}
