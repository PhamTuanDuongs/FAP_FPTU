/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.report;

import controller.authentication.BaseRequiredAuthenticatedControllerForStudent;
import dal.LectureDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import model.Lecture;
import model.User;

/**
 *
 * @author duong
 */
public class timetableForStudentsController extends BaseRequiredAuthenticatedControllerForStudent {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        request.getRequestDispatcher("view/lecture/lecture.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        String raw_std = request.getParameter("std");
        String raw_from = request.getParameter("dateFrom");
        String raw_to = request.getParameter("dateTo");
        LectureDBContext le = new LectureDBContext();
        ArrayList<Lecture> l = le.timetable(Integer.parseInt(raw_std), Date.valueOf(raw_from), Date.valueOf(raw_to));
        request.setAttribute("schedule", l);
        request.getRequestDispatcher("view/lecture/lecture.jsp").forward(request, response);
    }

}
