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
import model.Course;
import model.Department;
import model.Group;
import model.Instructor;
import model.Room;
import model.Session;
import model.TimeSlot;

/**
 *
 * @author duong
 */
public class timetableForInstructorDBContext extends DBContext<Session> {

    @Override
    public void insert(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Session get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Session> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Session> allSlotInWeek(int instructorId, Date from, Date to) {
        ArrayList<Session> lecture = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select *, DATEPART(WEEKDAY,Date) as WeekDay from\n"
                    + "Session l inner join [Group] g\n"
                    + "on g.GroupID = l.GroupID inner join Course c\n"
                    + "on g.CourseID = c.CourseID inner join Room r \n"
                    + "on l.RoomID = r.RoomID inner join TimeSlot t \n"
                    + "on  l.TimeSlotID = t.SlotID inner join Instructor i on l.InstructorID = i.InstructorID \n"
                    + "inner join Department d on i.Deptid = d.Deptid \n"
                    + "where l.InstructorID = ? and l.Date between ? and ? ";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, instructorId);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setDate(rs.getDate("Date"));
                s.setId(rs.getInt("SessionID"));
                s.setStatus(rs.getString("SessionStatus"));
                s.setWeekday(rs.getInt("WeekDay"));
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
                i.setFirstName(rs.getString("Firstname"));
                i.setLastName(rs.getString("Lastname"));
                i.setEmail(rs.getString("Email"));
                i.setDob(rs.getDate("DOB"));
                i.setGender(rs.getBoolean("Gender"));
                i.setAddress(rs.getString("Address"));
                i.setTelephone(rs.getString("Telephone"));

                Department d = new Department();
                d.setDeptId(rs.getInt("Deptid"));
                d.setDeptName(rs.getString("DeptName"));
                d.setDeptName(rs.getString("DeptCode"));
                i.setDepart(d);
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

                lecture.add(s);
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
        ArrayList<Session> lecture = t.allSlotInWeek(6, Date.valueOf("2023-03-20"), Date.valueOf("2023-03-24"));
        System.out.println(lecture.size());
        System.out.println(lecture.get(lecture.size()-1).getGroup().getCourse().getName());
        System.out.println(lecture.get(0).getSlot().getSlotId());
        System.out.println(lecture.get(0).getWeekday());
         
    }
}
