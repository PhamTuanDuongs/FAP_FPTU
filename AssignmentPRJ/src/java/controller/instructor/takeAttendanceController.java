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
import model.Attendance;
import model.Group;
import model.Instructor;
import model.Session;
import model.Student;
import model.TimeSlot;
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
        String[] indexs = request.getParameterValues("sid");
        int sessionid = Integer.parseInt(request.getParameter("sess"));
        ArrayList<Attendance> attendList = new ArrayList<>();
        for (String index : indexs) {
            Attendance a = new Attendance();
            Student st = new Student();
            st.setStudentid(Integer.parseInt(request.getParameter("studentid_" + index)));
            a.setStudent(st);
            Group g = new Group();
            g.setGroupId(Integer.parseInt(request.getParameter("groupid")));
            Session ss = new Session();
            ss.setId(Integer.parseInt(request.getParameter("sessionid")));
            ss.setGroup(g);
            TimeSlot slot = new TimeSlot();
            slot.setSlotId(Integer.parseInt(request.getParameter("slot")));
            ss.setSlot(slot);
            Instructor i = new Instructor();
            i.setInstructorId(user.getInstructorId());
            ss.setInstructor(i);
            a.setSession(ss);
            String raw_comment = request.getParameter("comment_" + index);
            a.setComment(raw_comment != null ? raw_comment : null);
            String raw_status = request.getParameter("status_" + index);
            a.setStatus(raw_status.equals("present") ? "attended" : raw_status);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            a.setRecordTime(timestamp);
            attendList.add(a);
        }
        takeAttendanceDBContext t = new takeAttendanceDBContext();
        t.takeAttendance(attendList, sessionid);
        response.sendRedirect("listattendancegroups");
//        response.getWriter().print(attendList.get(0).getComment()+"\n");
//        response.getWriter().print(attendList.get(0).getRecordTime()+"\n");
//        response.getWriter().print(attendList.get(0).getStatus()+"\n");
//        response.getWriter().print(attendList.get(0).getStudent().getStudentid()+"\n");
//        response.getWriter().print(attendList.get(0).getSession().getId()+"\n");
        
    }

}
