<%-- 
    Document   : attendanceReport
    Created on : Mar 1, 2023, 9:48:05 PM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table,thead,th,tbody,tr,td{
                border: 1px solid black;
            }
        </style>
    </head>
    <body>
        <form action="viewattendstudent" method="GET">
            <input type="hidden" name="std" value="${sessionScope.user.studentId}">
            Course: <select name="cid">
                <c:forEach items="${requestScope.course}" var="c">
                    <option value="${c.courseId}">${c.name}</option>
                </c:forEach>
                <input type="submit" value="View" />
            </select> 
        </form>
        <c:if test="${requestScope.attendance ne null}">
            <table>
                <thead>
                <th>DATE</th>
                <th>SLOT</th>
                <th>ROOM</th>
                <th>LECTURER</th>
                <th>GROUP NAME</th>
                <th>ATTEDANCE STATUS</th>
                <th>ECTURER'S COMMENT</th>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.attendance}" var="a">
                    <tr>
                        <td><fmt:formatDate value="${a.date}" pattern="EEEE - dd - MMMM - yyyy" /></td>
                        <td>${a.slot.slotId}_(${a.slot.timeFrom}-${a.slot.timeTo})</td>
                        <td>${a.room.rname}</td>
                        <td>${a.instructor}</td>
                        <td>${a.groupName}</td>
                        <td>${a.status}</td>
                        <td>${a.comment}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
