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
        <h1>Take Attendance</h1>
        <div class="title"><h1>FPT University Academic Portal</h1></div>        
        <div class="timetable">
            <form>
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
                        <c:set var="t" value="0"/>
                        <c:forEach items="${requestScope.list}" var="l">
                            <tr>
                                <c:set var="t" value="${t+1}"/>
                        <input type="hidden" name="studentid" value="${l.studentId}">
                        <input type="hidden" name="slot" value="${l.slotid}">
                        <td>${t}</td>
                        <td>${l.groupName}</td>
                        <td>${l.lastName}${' '}${l.firstName}</td>
                        <td>${l.rollnumber}</td>
                        <td><input type="radio" name="status" checked value="absent">absent</td>
                        <td><input type="radio" name="status" value="present">present</td>
                        <td><input style="border: 1px solid black; height: 30px; width: 100%" type="text" name="comment"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
