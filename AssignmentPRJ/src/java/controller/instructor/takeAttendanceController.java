/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.takeAttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import model.User;
import model.takeAttendance;

/**
 *
 * @author duong
 */
public class takeAttendanceController extends BaseRequiredAuthenticatedControllerForInstructor {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String[] indexs = request.getParameterValues("index");
        ArrayList<takeAttendance> attendList = new ArrayList<>();
        for (String index : indexs) {
            takeAttendance a = new takeAttendance();
            a.setStudentId(Integer.parseInt(request.getParameter("studentid_" + index)));
            a.setGroupId(Integer.parseInt(request.getParameter("groupid")));
            a.setSlotid(Integer.parseInt(request.getParameter("slot")));
            a.setLectureid(Integer.parseInt(request.getParameter("lectureid")));
            a.setInstructorid(user.getInstructorId());
            String raw_comment = request.getParameter("comment_" + index);
            a.setComment(raw_comment != null ? raw_comment : null);
            String raw_status = request.getParameter("status_" + index);
            a.setStatus(raw_status.equals("present") ? "attended" : raw_status);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            a.setTime(timestamp);
            attendList.add(a);
        }
        takeAttendanceDBContext t = new takeAttendanceDBContext();
        t.takeAttendance(attendList);
        String lecture = request.getParameter("lectureid");
        
        response.getWriter().print(lecture);
//        response.getWriter().print("Take attendance successfully");
          
        
    }
    
}
