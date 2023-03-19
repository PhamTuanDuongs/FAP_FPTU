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
            table{
                border-collapse: collapse;
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
                margin-bottom: 10rem;
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

            .button-take {
                align-items: center;
                appearance: none;
                background-image: radial-gradient(100% 100% at 100% 0, #5adaff 0, #5468ff 100%);
                border: 0;
                border-radius: 6px;
                box-shadow: rgba(45, 35, 66, .4) 0 2px 4px,rgba(45, 35, 66, .3) 0 7px 13px -3px,rgba(58, 65, 111, .5) 0 -3px 0 inset;
                box-sizing: border-box;
                color: #fff;
                cursor: pointer;
                display: inline-flex;
                font-family: "JetBrains Mono",monospace;
                height: 48px;
                justify-content: center;
                line-height: 1;
                list-style: none;
                overflow: hidden;
                padding-left: 16px;
                padding-right: 16px;
                position: relative;
                text-align: left;
                text-decoration: none;
                transition: box-shadow .15s,transform .15s;
                user-select: none;
                -webkit-user-select: none;
                touch-action: manipulation;
                white-space: nowrap;
                will-change: box-shadow,transform;
                font-size: 18px;
            }

            .button-take:focus {
                box-shadow: #3c4fe0 0 0 0 1.5px inset, rgba(45, 35, 66, .4) 0 2px 4px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #3c4fe0 0 -3px 0 inset;
            }

            .button-take:hover {
                box-shadow: rgba(45, 35, 66, .4) 0 4px 8px, rgba(45, 35, 66, .3) 0 7px 13px -3px, #3c4fe0 0 -3px 0 inset;
                transform: translateY(-2px);
            }

            .button-take:active {
                box-shadow: #3c4fe0 0 3px 7px inset;
                transform: translateY(2px);
            }
            .buttonss{
                margin-top: 10px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1 style="font-family: Helvetica,Arial,sans-serif;" >FPT University Academic Portal</h1></div>  
        <div  class="titel2">
            <h2 style="font-family: Helvetica,Arial,sans-serif;">Take Attendance</h1>
        </div>     
        <div class="timetable">
            <form action="add"  method="post">
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
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.list}" var="l" varStatus="loop">
                        <td>
                            <input type="hidden" name="studentid_${l.student.studentid}" value="${l.student.studentid}">
                            <input type="hidden" name="slot" value="${l.session.slot.slotId}">
                            <input type="hidden" name="sessionid" value="${l.session.id}">
                            <input type="hidden" name="index" value="${loop.index+1}">
                            <input type="hidden" name="groupid" value="${l.session.group.groupId}">
                            <input type="hidden" name="sid" value="${l.student.studentid}"/>
                        </td>
                        <tr>
                            <td>${loop.index+1}</td>
                            <td>${l.session.group.groupName}</td>
                            <td>${l.student.rollnumber}</td>
                            <td>${l.student.lastName}${' '}${l.student.firstName}</td>
                            <td style="height:162px"><img style="width: 100%; height: 100%;" src="http://localhost:9999/fap/view/img/${l.student.studentid}.jpg" alt=""></td>
                            <td><input type="radio" name="status_${l.student.studentid}" checked value="absent">absent</td>
                            <td><input type="radio" name="status_${l.student.studentid}" value="present">present</td>
                            <td><input style="height: 30px; width: 100%" placeholder="comment" type="text" name="comment_${l.student.studentid}"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" name="sess" value="${param.sessionid}"/>
                <div class="buttonss">
                    <input class="button-take" type="submit"  value="Add">
                </div>
            </form>
        </div>
    </body>
</html>
