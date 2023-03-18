/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.SessionDBContext;
import dal.listStudentDBContext;
import dal.takeAttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Attendance;
import model.Session;
import model.Student;
import model.User;

/**
 *
 * @author duong
 */
public class statusController extends BaseRequiredAuthenticatedControllerForInstructor {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String raw_groupid = request.getParameter("groupid");
        try {
            int groupid = Integer.parseInt(raw_groupid);
            
            SessionDBContext ss = new SessionDBContext();
            ArrayList<Session> all = ss.allByGroupId(groupid);
            request.setAttribute("listSession", all);
            
            listStudentDBContext l = new listStudentDBContext();
             ArrayList<Student> student = l.studentByGroupID(groupid);
            request.setAttribute("list", student);
            
            takeAttendanceDBContext status = new takeAttendanceDBContext();
            ArrayList<Attendance> attend = status.statusStudents(groupid);
            request.setAttribute("listStudent", attend);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        
        request.getRequestDispatcher("view/Instructor/status.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {

    }

}
