/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.ListAttendanceGroupsDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.User;
import model.listAttendaneGroups;

/**
 *
 * @author duong
 */
public class listAttendanceGroupsForTodayController extends BaseRequiredAuthenticatedControllerForInstructor {
     protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            User user = (User)req.getSession().getAttribute("user");
            LocalDate currentdate = LocalDate.now();
            // get current day;
            ListAttendanceGroupsDBContext l = new ListAttendanceGroupsDBContext();
            ArrayList<listAttendaneGroups> list = l.allGroup(Date.valueOf("2023-03-20"), user.getInstructorId());
            req.setAttribute("list", list);
            req.getRequestDispatcher("view/Instructor/listAttendanceGroupsForToday.jsp").forward(req, resp);
     }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }
    
}
