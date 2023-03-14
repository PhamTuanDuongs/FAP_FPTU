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
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Attendance;
import model.Group;
import model.Instructor;
import model.Session;
import model.Student;
import model.TimeSlot;
import model.User;

/**
 *
 * @author duong
 */
public class updateAttendanceController extends BaseRequiredAuthenticatedControllerForInstructor {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String raw_instructor = request.getParameter("instructor");
        String raw_groupid = request.getParameter("groupid");
        String raw_slot = request.getParameter("slot");
        String raw_lectureid = request.getParameter("sessionid");
        try {
            int groupid = Integer.parseInt(raw_groupid);
            int instructor = Integer.parseInt(raw_instructor);
            int slot = Integer.parseInt(raw_slot);
            int lectureid = Integer.parseInt(raw_lectureid);
            LocalDate currentdate = LocalDate.now();
            takeAttendanceDBContext t = new takeAttendanceDBContext();
            ArrayList<Attendance> liststudent = t.viewAttendance(Date.valueOf("2023-03-20"), groupid, instructor, slot, lectureid);
            request.setAttribute("liststudent", liststudent);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("view/Instructor/updateAttendance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String[] indexs = request.getParameterValues("sid");
        ArrayList<Attendance> attendList = new ArrayList<>();
        int group_id = 0;
        int session_id = 0;
        int instructor_id = 0;
        int slot_id = 0;
        for (String index : indexs) {
            Attendance a = new Attendance();
            Student st = new Student();
            st.setStudentid(Integer.parseInt(request.getParameter("studentid_" + index)));
            a.setStudent(st);
            Group g = new Group();
            g.setGroupId(Integer.parseInt(request.getParameter("groupid")));
            group_id = g.getGroupId();
            Session ss = new Session();
            ss.setId(Integer.parseInt(request.getParameter("sessionid")));
            session_id = ss.getId();
            ss.setGroup(g);
            TimeSlot slot = new TimeSlot();
            slot.setSlotId(Integer.parseInt(request.getParameter("slot")));
            slot_id = slot.getSlotId();
            ss.setSlot(slot);
            Instructor i = new Instructor();
            i.setInstructorId(user.getInstructorId());
            instructor_id = i.getInstructorId();
            ss.setInstructor(i);
            a.setSession(ss);
            String raw_comment = request.getParameter("comment_" + index);
            a.setComment(raw_comment != null ? raw_comment : "");
            String raw_status = request.getParameter("status_" + index);
            a.setStatus(raw_status.equals("present") ? "attended" : raw_status);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            a.setRecordTime(timestamp);
            attendList.add(a);
        }
        takeAttendanceDBContext t = new takeAttendanceDBContext();
        t.updateAttendance(attendList);
        response.sendRedirect("listattendancegroups");
    }

}
