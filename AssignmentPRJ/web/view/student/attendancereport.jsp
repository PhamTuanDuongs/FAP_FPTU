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
        <title>Attendance Report</title>
        <style>
            *{
                margin: 0;
                border: 0;
                padding: 0;
            }
            table,thead,th,tbody,tr,td{
                border: 1px solid black;
            }
            thead{
                background-color: #655DBB;
            }
            th{
                width: 300px;
                height: 50px;
            }
            .timetable{
                width: 66rem;
                margin-left:350px;
                margin-top:30px;
                border: 1px solid red;
            }
            .formm{
                width: 700px;
                margin-left:350px;
            }
            select {
                border:1px solid black;
            }
            .submit{
                margin-left: 3px;
                padding: 3px;
                background-color: #A084DC;
                color: white;
                cursor: pointer;
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
        <!--<div class="title"><h1>FPT University Academic Portal</h1></div>-->        
        <div class="formm">
            <form action="viewattendstudent" method="GET">
                <input type="hidden" name="studenId" value="${sessionScope.user.studentId}">
                Choose the course: <select name="courseId">
                    <c:forEach items="${requestScope.course}" var="c">
                        <c:set var="course" value="${requestScope.courseid}"/>
                        <option value="${c.course.courseId}"  ${course == c.course.courseId ? 'selected':''}   >${c.groupName}(${c.course.name})</option>
                    </c:forEach>
                    <input class="submit" type="submit" value="View" />
                </select> 
            </form>
        </div>

        <c:if test="${requestScope.attendance ne null}">
            <div class="timetable">
                <table>
                    <thead>
                    <th>NO</th>
                    <th>DATE</th>
                    <th>SLOT</th>
                    <th>ROOM</th>
                    <th>LECTURER</th>
                    <th>GROUP NAME</th>
                    <th>ATTEDANCE STATUS</th>
                    <th>ECTURER'S COMMENT</th>
                    </thead>
                    <tbody>
                        <c:set var="t" value="0"/>
                        <c:forEach items="${requestScope.attendance}" var="a">
                            <tr>
                                <c:set var="t" value="${t+1}"/>
                                <td>${t}</td>
                                <td><fmt:formatDate value="${a.session.date}" pattern="EEEE dd/MMMM/yyyy" /></td>
                                <td>${a.session.slot.slotId}_(${a.session.slot.timeFrom}-${a.session.slot.timeTo})</td>
                                <td>${a.session.room.rname}</td>
                                <td>${a.session.instructor.instrnumber}</td>
                                <td>${a.session.group.groupName}</td>
                                <td>${a.status}</td>
                                <td>${a.comment}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </body>
</html>
