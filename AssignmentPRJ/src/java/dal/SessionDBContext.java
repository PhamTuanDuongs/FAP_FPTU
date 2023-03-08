/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class SessionDBContext extends DBContext<Session> {

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

    public ArrayList<Session> timetable(int std, Date from, Date to) {
        ArrayList<Session> lecture = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select *,DATEPART(WEEKDAY,Date) as WeekDay from Lecture l inner join Attendance a\n"
                    + "on l.LectureID  = a.LectureID inner join [Group] g \n"
                    + "on l.GroupID = g.GroupID join Course c on \n"
                    + "g.CourseID = c.CourseID join Room r\n"
                    + "on l.RoomID = r.RoomID join TimeSlot s\n"
                    + "on l.TimeSlotID = s.SlotID\n"
                    + "where a.StudentID = ? and l.[Date] between ? and ?";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, std);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                Session l = new Session();
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

                lecture.add(l);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SessionDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lecture;
    }

    public static void main(String[] args) {
//        ArrayList<String> date = getEachDayByWeek(12);
//        String dateFrom = date.get(0);
//        String dateTo = date.get(date.size() - 1);
//        LectureDBContext le = new LectureDBContext();
//        ArrayList<Lecture> l = le.timetable(1, Date.valueOf(dateFrom), Date.valueOf(dateTo));
//        if (l != null) {
//            System.out.println(l.size());
//        } else {
//
//        }
        SessionDBContext le = new SessionDBContext();
        ArrayList<Session> l = le.timetable(1, Date.valueOf("2023-03-20"), Date.valueOf("2023-03-24"));
        System.out.println(l.size());

    }

    public static ArrayList<String> getEachDayByWeek(int weekNumber) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ArrayList<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < 7; i++) {
            String date;
            date = sdf.format(cal.getTime());
            list.add(date);
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

}
