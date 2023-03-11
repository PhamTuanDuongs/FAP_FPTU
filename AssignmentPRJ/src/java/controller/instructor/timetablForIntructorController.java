/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.instructor;

import controller.authentication.BaseRequiredAuthenticatedControllerForInstructor;
import dal.TimeSlotDBContext;
import dal.timetableForInstructorDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import model.Session;
import model.TimeSlot;
import model.User;

/**
 *
 * @author duong
 */
public class timetablForIntructorController extends BaseRequiredAuthenticatedControllerForInstructor {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String raw_week = req.getParameter("week");
        String year_raw = req.getParameter("year");
        
        TimeSlotDBContext timeDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = timeDB.all();
        req.setAttribute("slots", slots);
        
        LocalDate currentdate = LocalDate.now();
        int currentYear = currentdate.getYear();
        int currYearrr = currentdate.getYear();
        int[] listYear = {2021, 2022, 2023, 2024};
        req.setAttribute("listYear", listYear);
        ArrayList<String> list = new ArrayList<>();
        if (year_raw == null) {
            // get current year
            list = getAllDayWeek(currentYear);
        } else {
            // get year when year_raw not null
            currentYear = Integer.parseInt(year_raw);
            list = getAllDayWeek(currentYear);
        }
        req.setAttribute("yearCurrent", currentYear);
        req.setAttribute("list", list);
        if (raw_week == null && currentYear == currYearrr) {
            int currentWeek = getCurrentWeek();
            ArrayList<String> allDay = getEachDayByWeek(currentWeek, currentYear);
            req.setAttribute("current", currentWeek);
            req.setAttribute("days", allDay);
        }else if (raw_week != null && currentYear == currYearrr) {
            ArrayList<String> allDay = getEachDayByWeek(Integer.parseInt(raw_week), currentYear);
            req.setAttribute("current", Integer.parseInt(raw_week));
            req.setAttribute("days", allDay);
        } else if (raw_week != null && currentYear != currYearrr) {
            ArrayList<String> allDay = getEachDayByWeek(Integer.parseInt(raw_week), currentYear);
            req.setAttribute("current", Integer.parseInt(raw_week));
            req.setAttribute("days", allDay);
        }

        if (raw_week != null) {
            ArrayList<String> allDay = getEachDayByWeekIndb(Integer.parseInt(raw_week), currentYear);
            String dateFrom = allDay.get(0);
            String dateTo = allDay.get(allDay.size() - 1);
            timetableForInstructorDBContext t = new timetableForInstructorDBContext();
            User user = (User) req.getSession().getAttribute("user");
            ArrayList<Session> l = t.allSlotInWeek(user.getInstructorId(), Date.valueOf(dateFrom), Date.valueOf(dateTo));
            if (l == null) {

            } else {
                req.setAttribute("schedule", l);
            }
        } else {
            int currentw = getCurrentWeek();
            ArrayList<String> date = getEachDayByWeekIndb(currentw, currentYear);
            String dateFrom = date.get(0);
            String dateTo = date.get(date.size() - 1);
            timetableForInstructorDBContext t = new timetableForInstructorDBContext();
            User user = (User) req.getSession().getAttribute("user");
            ArrayList<Session> l = t.allSlotInWeek(user.getInstructorId(), Date.valueOf(dateFrom), Date.valueOf(dateTo));

            if (l == null) {

            } else {
                req.setAttribute("schedule", l);
            }

        }

        req.getRequestDispatcher("view/Instructor/timetable.jsp").forward(req, resp);

    }

    private int getCurrentWeek() {
        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        int currentWeek = cal.get(Calendar.WEEK_OF_YEAR);
        return currentWeek;
    }

    private int getTotalWeeksInYear(int year) {
        int totalWeeks = 0;
        Calendar calendar = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            int day = 1;
            do {
                calendar.set(year, month, day);
                if (calendar.get(Calendar.DAY_OF_WEEK) == 5) {
                    totalWeeks++;
                }
                day++;
            } while (day <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return totalWeeks;
    }

    private ArrayList<String> getAllDayWeek(int year) {
        ArrayList<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        int totalWeek = getTotalWeeksInYear(year);
        for (int i = 1; i <= totalWeek; i++) {
            Calendar cal = Calendar.getInstance(Locale.GERMANY);
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.WEEK_OF_YEAR, i);
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            String weekday = "";
            weekday = weekday + sdf.format(cal.getTime()) + "/";
            cal.add(Calendar.DATE, 6);
            weekday += sdf.format(cal.getTime());
            list.add(weekday);
        }
        return list;

    }

    private ArrayList<String> getEachDayByWeek(int weekNumber, int year) {
        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        ArrayList<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        for (int i = 0; i < 7; i++) {
            String date;
            date = sdf.format(cal.getTime());
            list.add(date);
            cal.add(Calendar.DATE, 1);
        }
        return list;
    }

    private ArrayList<String> getEachDayByWeekIndb(int weekNumber, int year) {
        Calendar cal = Calendar.getInstance(Locale.GERMANY);
        cal.set(Calendar.YEAR, year);
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
        processRequest(request, response);
    }

}
