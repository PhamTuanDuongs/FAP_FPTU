<%-- 
    Document   : takeAttendance
    Created on : Mar 6, 2023, 9:16:52 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                background-color: #8077e0;
            }
            th{
                width: 300px;
                height: 50px;
            }
            .timetable{
                width: 66rem;
                margin-left:350px;
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
                padding-left: 22rem;
                height: 100px;
                display:flex;
                align-items: center;
            }
            .titel2{
                padding-left: 22rem;
                height: 100px;
                display: flex;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1>FPT University Academic Portal</h1></div>  
        <div  class="titel2">
            <h2>Take Attendance</h1>
        </div>     
        <div class="timetable">
            <form action="add"  method="post">
                <table>
                    <thead>
                    <th>NO</th>
                    <th>Group</th>
                    <th>Name</th>
                    <th>Rollnumber</th>
                    <th>Absent</th>
                    <th>Present</th>
                    <th>Comment</th>
                    <th>Image</th>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.list}" var="l" varStatus="loop">
                            <tr>
                        <input type="hidden" name="studentid_${l.student.studentid}" value="${l.student.studentid}">
                        <input type="hidden" name="slot" value="${l.session.slot.slotId}">
                        <input type="hidden" name="sessionid" value="${l.session.id}">
                        <input type="hidden" name="index" value="${loop.index+1}">
                        <input type="hidden" name="groupid" value="${l.session.group.groupId}">
                        <td>${loop.index+1}</td>
                        <td>${l.session.group.groupName}</td>
                        <td>${l.student.lastName}${' '}${l.student.firstName}</td>
                        <td>${l.student.rollnumber}</td>
                        <input type="hidden" name="sid" value="${l.student.studentid}"/>
                        <td><input type="radio" name="status_${l.student.studentid}" checked value="absent">absent</td>
                        <td><input type="radio" name="status_${l.student.studentid}" value="present">present</td>
                        <td><input style="border: 1px solid black; height: 30px; width: 100%" type="text" name="comment_${l.student.studentid}"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                 <input type="hidden" name="sess" value="${param.sessionid}"/>
                <input type="submit"  value="Submit">
            </form>
        </div>
    </body>
</html>
