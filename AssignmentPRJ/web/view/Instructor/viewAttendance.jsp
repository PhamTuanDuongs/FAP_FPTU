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
                border-collapse: collapse;
                width: 100%;
            }
            th{
                border: 1px solid gray;
                width: 300px;
                height: 50px;
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
                margin-left:350px;
                margin-bottom: 10rem;
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
                color: #3E54AC;
            }
            
            .button{
                text-align: center;
                margin-top: 1rem;
            }

            .button-5 {
                align-items: center;
                background-clip: padding-box;
                background-color: #3E54AC;
                border: 1px solid transparent;
                border-radius: .25rem;
                box-shadow: rgba(0, 0, 0, 0.02) 0 1px 3px 0;
                box-sizing: border-box;
                color: #fff;
                cursor: pointer;
                display: inline-flex;
                font-family: system-ui,-apple-system,system-ui,"Helvetica Neue",Helvetica,Arial,sans-serif;
                font-size: 16px;
                font-weight: 600;
                justify-content: center;
                line-height: 1.25;
                margin: 0;
                min-height: 3rem;
                padding: calc(.875rem - 1px) calc(1.5rem - 1px);
                position: relative;
                text-decoration: none;
                transition: all 250ms;
                -webkit-user-select: none;
                vertical-align: baseline;
                width: auto;
            }

            .button-5:hover,
            .button-5:focus {
                background-color: #655DBB;
                box-shadow: rgba(0, 0, 0, 0.1) 0 4px 12px;
            }

            .button-5:hover {
                transform: translateY(-1px);
            }

            .button-5:active {
                background-color: #3E54AC;
                box-shadow: rgba(0, 0, 0, .06) 0 2px 4px;
                transform: translateY(0);
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
                <th>Rollnumber</th>
                <th>Name</th>
                <th>Image</th>
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
                            <td>${l.student.rollnumber}</td>
                            <td style="width:40rem">${l.student.lastName}${' '}${l.student.firstName}</td>
                            <td style="width:31rem;height:162px"><img style="width: 100%; height: 100%;" src="http://localhost:9999/fap/view/img/${l.student.studentid}.jpg" alt=""></td>
                            <td><input readonly="" ${l.status eq "absent" ? 'checked':'disabled'}  type="radio" value="absent" >absent</td>
                            <td><input readonly="" ${l.status eq "attended" ? 'checked':'disabled'}  type="radio"  value="present">present</td>
                            <td><input type="text" readonly="" value="${l.comment}"> </td>
                            <td style="width:40rem">${l.recordTime}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
                     <div class="button"><a class="button-5" href="http://localhost:9999/fap/listattendancegroups">BACK</a></div>
        </div>
    </body>
</html>
