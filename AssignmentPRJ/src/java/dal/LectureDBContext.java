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
import model.Lecture;

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
            String sql = "select g.Name,c.Code,r.rname,a.Status,l.TimeSlotID,DATEPART(WEEKDAY,Date) as WeekDay from Lecture l inner join Attendance a\n"
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
        LectureDBContext le = new LectureDBContext();
        ArrayList<Lecture> l = le.timetable(1, Date.valueOf("2023-03-20"), Date.valueOf("2023-03-24"));
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
