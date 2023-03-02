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
import model.Group;
import model.Lecture;
import model.Room;
import model.TimeSlot;

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

    public ArrayList<Lecture> timetable(int std, Date from, Date to) {
        ArrayList<Lecture> lecture = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "Select l.LectureID,l.[Date],c.CourseID,c.Code,c.Credit,r.RoomID,r.rname,\n"
                    + "g.Name,t.SlotID,t.TimeFrom,t.TimeTo,g.GroupID,i.instrnumber,a.[Status] from \n"
                    + " Lecture l inner join [Group] g \n"
                    + "on l.GroupID = g.GroupID inner join TimeSlot t \n"
                    + " on l.TimeSlotID = t.SlotID inner join Course c\n"
                    + " on g.CourseID = c.CourseID inner join Room r\n"
                    + "  on l.RoomID = r.RoomID inner join StudentGroup sg \n"
                    + " on sg.GroupID = g.GroupID inner join Student s \n"
                    + " on sg.StudentID = s.StudentID  \n"
                    + " inner join Attendance a on s.StudentID = a.StudentID inner join Instructor i\n"
                    + "on l.InstructorID = i.InstructorID  \n"
                    + " where s.StudentID = ? and l.[Date] between ? and ? \n"
                    + "group by l.LectureID,l.Date,c.CourseID,c.Code,c.Credit,r.RoomID,r.rname,\n"
                    + "g.Name,t.SlotID,t.TimeFrom,t.TimeTo,g.GroupID,i.instrnumber,a.[Status] ORDER BY l.Date";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, std);
            stm.setDate(2, from);
            stm.setDate(3, to);
            rs = stm.executeQuery();
            while (rs.next()) {
                Lecture l = new Lecture();
                l.setDate(rs.getDate("Date"));
                l.setInstructor(rs.getString("instrnumber"));
                l.setStatus(rs.getString("Status"));
                l.setLessonId(rs.getInt("LectureID"));
                Course c = new Course();
                c.setCourseId(rs.getInt("CourseID"));
                c.setCode(rs.getString("Code"));
                c.setCredit(rs.getInt("Credit"));
                l.setCourse(c);
                Room r = new Room();
                r.setRoomId(rs.getInt("RoomID"));
                r.setRname(rs.getString("rname"));
                l.setRoom(r);
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("SlotID"));
                t.setTimeFrom(rs.getTime("TimeFrom"));
                t.setTimeTo(rs.getTime("TimeTo"));
                l.setTimeSlot(t);
                Group g = new Group();
                g.setCourse(c);
                g.setGroupId(rs.getInt("GroupID"));
                g.setGroupName(rs.getString("Name"));
                l.setGroup(g);
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
        ArrayList<String> date = getEachDayByWeek(12);
        String dateFrom = date.get(0);
        String dateTo = date.get(date.size() - 1);
        LectureDBContext le = new LectureDBContext();
        ArrayList<Lecture> l = le.timetable(1, Date.valueOf(dateFrom), Date.valueOf(dateTo));
        if (l!=null) {
            System.out.println(l.size());
        }else{
            
        }
//      
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
