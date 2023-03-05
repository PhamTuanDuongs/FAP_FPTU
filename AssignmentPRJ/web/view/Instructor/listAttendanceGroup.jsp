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
    </head>
    <body>
        <table>
            <thead>
            <th>TimeFrom</th>
            <th>TimeTo</th>
            <th>Course</th>
            <th>Group</th>
            <th>Room</th>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.list}" var="l">
                    <tr>
                        <td>${l.time.timeFrom}</td>
                        <td>${l.time.timeTo}</td>
                        <td>${l.courseName}</td>
                        <td>${l.groupName}</td>
                        <td>${l.roomName}</td>
                        <td>${l.groupId}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
