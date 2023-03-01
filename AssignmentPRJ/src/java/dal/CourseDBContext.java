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
import model.Course;

/**
 *
 * @author duong
 */
public class CourseDBContext extends DBContext<Course> {

    @Override
    public void insert(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Course model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Course> all() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Course> allCourseByStudentId(int id) {
        ArrayList<Course> course = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            String sql = "select  c.Code,c.CourseID,c.Credit,c.Description,c.Name\n"
                    + "from\n"
                    + "Lecture l join [Group] g\n"
                    + "on l.GroupID = g.GroupID join TimeSlot t \n"
                    + "on l.TimeSlotID = t.SlotID join Course c \n"
                    + "on g.CourseID = c.CourseID join Room r\n"
                    + "on l.RoomID = r.RoomID join StudentGroup sg \n"
                    + "on sg.GroupID = g.GroupID join Student s \n"
                    + "on sg.StudentID = s.StudentID join Attendance a \n"
                    + "on s.StudentID = a.StudentID join Instructor i\n"
                    + "on l.InstructorID = i.InstructorID\n"
                    + "where s.StudentID  = ?\n"
                    + "group by c.Code,c.CourseID,c.Credit,c.Description,c.Name";
            stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                Course c = new Course();
                c.setCode(rs.getString("Code"));
                c.setCourseId(rs.getInt("CourseID"));
                c.setCredit(rs.getInt("Credit"));
                c.setName(rs.getString("Name"));
                course.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LectureDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                stm.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return course;
    }
    
    public static void main(String[] args) {
        CourseDBContext c = new CourseDBContext();
         ArrayList<Course> course = c.allCourseByStudentId(1);
         System.out.println(course.get(0).getName());
         System.out.println(course.get(1).getName());
         System.out.println(course.get(2).getName());
         System.out.println(course.get(3).getName());
    }

}
