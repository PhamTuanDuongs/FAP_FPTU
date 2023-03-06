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
import model.TimeSlot;
import model.listAttendaneGroups;

/**
 *
 * @author duong
 */
public class ListAttendanceGroupsDBContext extends DBContext<listAttendaneGroups> {

    @Override
    public void insert(listAttendaneGroups model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(listAttendaneGroups model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(listAttendaneGroups model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public listAttendaneGroups get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<listAttendaneGroups> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<listAttendaneGroups> allGroup(Date date, int instructorId) {
        ArrayList<listAttendaneGroups> list = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "select l.LectureID, l.InstructorID,l.TimeSlotID, t.SlotID, t.TimeFrom,t.TimeTo,c.Name as 'nameCourse',g.Name,g.GroupID,r.rname from Lecture l inner join Instructor i\n"
                    + "on l.InstructorID = i.InstructorID inner join [Group] g\n"
                    + "on l.GroupID = g.GroupID join TimeSlot t\n"
                    + "on l.TimeSlotID = t.SlotID inner join Course c \n"
                    + "on g.CourseID = c.CourseID join Room r \n"
                    + "on l.RoomID = r.RoomID\n"
                    + "where l.Date = ? and l.InstructorID = ?";
            stm = connection.prepareStatement(sql);
            stm.setDate(1, date);
            stm.setInt(2, instructorId);
            rs = stm.executeQuery();
            while (rs.next()) {
                listAttendaneGroups l = new listAttendaneGroups();
                TimeSlot t = new TimeSlot();
                t.setSlotId(rs.getInt("TimeSlotID"));
                t.setTimeFrom(rs.getTime("TimeFrom"));
                t.setTimeTo(rs.getTime("TimeTo"));
                l.setTime(t);
                l.setCourseName(rs.getString("nameCourse"));
                l.setGroupName(rs.getString("Name"));
                l.setRoomName(rs.getString("rname"));
                l.setGroupId(rs.getInt("GroupID"));
                l.setInstructor(rs.getInt("InstructorID"));
                l.setLectureid(rs.getInt("LectureID"));
                list.add(l);
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
        ArrayList<listAttendaneGroups> l = t.allGroup(Date.valueOf("2023-03-20"), 6);
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i).getGroupName());
            System.out.println(l.get(i).getCourseName());
            System.out.println(l.get(i).getTime().getSlotId());
        }
    }
}
