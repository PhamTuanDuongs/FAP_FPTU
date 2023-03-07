<%-- 
    Document   : listAttendanceGroup
    Created on : Mar 5, 2023, 10:09:24 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GroupAttendance</title>
        <style>
            table{
                border: 1px solid black;
                width: 70rem;
                height: 15rem;
            }
            thead{
                background-color: #655DBB;
            }
            thead,tbody,tr,td{
                border: 1px solid black;
            }
            .timetable{
                width: 70rem;
                margin-left:300px;
                margin-top:100px;
                border: 1px solid red;
            }

            .title{
                padding-left: 23rem;
                height: 100px;
                display:flex;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1>FPT University Academic Portal</h1></div>
        <div class="timetable">
            <table>
                <thead>
                <th>TimeFrom</th>
                <th>TimeTo</th>
                <th>Course</th>
                <th>Group</th>
                <th>Room</th>
                <th>Take Attendance</th>
                <th>View Attendance</th>
                <th>Edit Attendance</th>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.list}" var="l">
                        <tr>
                            <td>${l.time.timeFrom}</td>
                            <td>${l.time.timeTo}</td>
                            <td>${l.courseName}</td>
                            <td>${l.groupName}</td>
                            <td>${l.roomName}</td>
                            <td>
                                <form action="attendance" method="post">
                                    <input type="hidden" name="instructor" value="${l.instructor}" >
                                    <input type="hidden" name="groupid" value="${l.groupId}" >
                                    <input type="hidden" name="slot" value="${l.time.slotId}" >
                                    <input type="hidden" name="lectureid" value="${l.lectureid}" >
                                    <input type="submit" value="Take" >
                                </form>
                            </td>
                            <td>
                                <form action="viewattendance" method="post">
                                    <input type="hidden" name="instructor" value="${l.instructor}" >
                                    <input type="hidden" name="groupid" value="${l.groupId}" >
                                    <input type="hidden" name="slot" value="${l.time.slotId}" >
                                    <input type="hidden" name="lectureid" value="${l.lectureid}" >
                                    <input type="submit" value="View" >
                                </form>
                            </td>
                            <td>
                                <form action="editattendance" method="post">
                                    <input type="hidden" name="instructor" value="${l.instructor}" >
                                    <input type="hidden" name="groupid" value="${l.groupId}" >
                                    <input type="hidden" name="slot" value="${l.time.slotId}" >
                                    <input type="hidden" name="lectureid" value="${l.lectureid}" >
                                    <input type="submit" value="Edit" >
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
