<%-- 
    Document   : viewAttendance
    Created on : Mar 7, 2023, 4:13:59 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewAttendance</title>
        <style>
            table{
                border: 1px solid gray;
                width: 70rem;
                height: 30rem;
                border-collapse: collapse;
            }
            th{
                border: 1px solid gray;
            }
            thead{
                height: 50px;
                text-align: left;
                background-color: #837be1;
            }
            thead,tbody,tr,td{
                border: 1px solid gray;
            }
            .timetable{
                width: 70rem;
                margin-left:300px;
            }

            .title{
                padding-left: 19rem;
                height: 100px;
                display:flex;
                align-items: center;
            }
            .titel2{
                padding-left: 19rem;
                height: 100px;
                display: flex;
                align-items: center;
                color: #3E54AC;
            }
        </style> 
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1>FPT University Academic Portal</h1></div>  
        <div  class="titel2">
            <h2>ViewAttendance</h1>
        </div>
        <div class="timetable">
            <table>
                <thead>
                <th>NO</th>
                <th>Group</th>
                <th>Name</th>
                <th>Rollnumber</th>
                <th>Absent</th>
                <th>Present</th>
                <th>Comment</th>
                <th>RecordTime</th>
                <!--<th>Image</th>-->
                </thead>
                <tbody>
                    <c:set var="index" value="0"/>
                    <c:forEach items="${requestScope.liststudent}" var="l">
                        <tr>
                            <c:set var="index" value="${index+1}"/>
                            <td>${index}</td>
                            <td>${l.session.group.groupName}</td>
                            <td>${l.student.lastName}${' '}${l.student.firstName}</td>
                            <td>${l.student.rollnumber}</td>
                            <td><input readonly="" ${l.status eq "absent" ? 'checked':'disabled'}  type="radio" value="absent" >absent</td>
                            <td><input readonly="" ${l.status eq "attended" ? 'checked':'disabled'}  type="radio"  value="present">present</td>
                            <td><input type="text" readonly="" value="${l.comment}"> </td>
                            <td>${l.recordTime}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
