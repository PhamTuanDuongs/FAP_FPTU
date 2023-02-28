<%-- 
    Document   : lecture
    Created on : Feb 27, 2023, 9:26:49 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <style>
            table,tr,td{
                border: 1px solid black;
            }
        </style> 
    </head>
    <body>
        <!--        <div class="navigation">
                    <ul>
                        <li class="list active">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-user"></i></span>
                                <span class="title">User</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-calendar"></i></span>
                                <span class="title">Weekly Timetable</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-user-check"></i></span>
                                <span class="title">Attendance Report</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-clipboard-user"></i></span>
                                <span class="title">Take attendance</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-right-from-bracket"></i></span>
                                <span class="title">Sign Out</span>
                            </a>
                        </li>
                    </ul>
        
                    <div class="toggle"></div>
                </div>-->
        <div class="form">
            <form action="schedule" method="POST">
                <label>Lecturer</label>
                <input type="text" name="rollnumber" /><br>
                <label>From:</label>
                <input type="date" name="dateFrom" />
                <label>To:</label>
                <input type="date" name="dateTo" />
                <input type="submit" value="View" />
            </form>
        </div>

        <table>
            <tr>
                <td></td>
                <c:forEach items="${requestScope.schedule}" var="l">
                    <c:if test="${l.slotId eq 1}">  
                        <td><fmt:formatDate value="${l.date}" pattern="EEEE - dd - MMMM - yyyy" /></td>
                    </c:if> 
                </c:forEach>
            </tr>
            <tr>
                <td>Slot1 7:30-9h50</td>
                <c:forEach items="${requestScope.schedule}" var="l">
                    <c:if test="${l.slotId eq 1}">  
                        <td>${l.group}<br>
                            ${l.slotId}<br>
                            ${l.course}<br>
                            ${l.room}<br>
                            ${l.status}</td>
                        </c:if> 
                    </c:forEach>

            </tr>
            <tr>
                <td>Slot1 10h-12h20</td>
                <c:forEach items="${requestScope.schedule}" var="l">
                    <c:if test="${l.slotId eq 2}">  
                        <td>${l.group}<br>
                            ${l.slotId}<br>
                            ${l.course}<br>
                            ${l.room}<br>
                            ${l.status}</td>
                        </c:if> 
                    </c:forEach>

            </tr>
            <tr>
                <td>Slot1 12h50-15h10</td>
                <c:forEach items="${requestScope.schedule}" var="l">
                    <c:if test="${l.slotId eq 3}">  
                        <td>${l.group}<br>
                            ${l.slotId}<br>
                            ${l.course}<br>
                            ${l.room}<br>
                            ${l.status}</td>
                        </c:if> 
                    </c:forEach>

            </tr>
            <tr>
                <td>Slot1 15h20-17h40</td>
                <c:forEach items="${requestScope.schedule}" var="l">
                    <c:if test="${l.slotId eq 4}">  
                        <td>${l.group}<br>
                            ${l.slotId}<br>
                            ${l.course}<br>
                            ${l.room}<br>
                            ${l.status}</td>
                        </c:if> 
                    </c:forEach>

            </tr>

        </table>

    </body>
</html>
