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
            thead{
                background-color: #655DBB;
            }
            th{
                 width: 300px;
                 height: 50px;
            }
            .timetable{
                width: 66rem;
                margin-left:400px;
                margin-top:30px;
                border: 1px solid red;
            }
            .formm{
                width: 700px;
                margin-left:500px;
                margin-top:30px;
            }
        </style>
    </head>
    <body>
        <div class="formm">
            <form action="viewattendstudent" method="GET">
                <input type="hidden" name="studenId" value="${sessionScope.user.studentId}">
              Choose the course: <select name="courseId">
                    <c:forEach items="${requestScope.course}" var="c">
                        <c:set var="course" value="${requestScope.courseid}"/>
                        <option value="${c.courseId}"  ${course == c.courseId ? 'selected':''}   >${c.name}(${c.code})(${c.groupname},start ${c.date} )</option>
                    </c:forEach>
                    <input type="submit" value="View" />
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
                                <td><fmt:formatDate value="${a.date}" pattern="EEEE dd/MMMM/yyyy" /></td>
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
            </div>
        </c:if>
    </body>
</html>