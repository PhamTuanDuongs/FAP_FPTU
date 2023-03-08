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
public class ListAttendanceGroupsDBContext extends DBContext<Session> {

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

    public ArrayList<Session> allGroup(Date date, int instructorId) {
        ArrayList<Session> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select * from Lecture l inner join Instructor i \n"
                    + "                     on l.InstructorID = i.InstructorID inner join [Group] g \n"
                    + "                     on l.GroupID = g.GroupID join TimeSlot t  \n"
                    + "                     on l.TimeSlotID = t.SlotID inner join Course c  \n"
                    + "                     on g.CourseID = c.CourseID join Room r  \n"
                    + "                     on l.RoomID = r.RoomID inner join Department d  on i.Deptid = d.Deptid  \n"
                    + "                     where l.Date = ? and l.InstructorID = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, instructorId);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session s = new Session();
                s.setDate(rs.getDate("Date"));

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

                list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ListAttendanceGroupsDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        ListAttendanceGroupsDBContext t = new ListAttendanceGroupsDBContext();
        ArrayList<Session> l = t.allGroup(Date.valueOf("2023-03-20"), 6);
        System.out.println(l.size());
        for (int i = 0; i < l.size(); i++) {
            
        }
    }
}
