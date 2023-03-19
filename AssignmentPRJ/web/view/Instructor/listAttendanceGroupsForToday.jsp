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
                border-collapse: collapse;
                text-align: center;
            }
            thead{
                background-color: #837be1;
                height: 50px;
            }
            thead,tbody,tr,td{
                border: 1px solid black;
            }
            .timetable{
                width: 70rem;
                margin-left:330px;
            }

            .title{
               
                padding-left: 20.5rem;
                height: 100px;
                display:flex;
                align-items: center;
            }
            .button-take {
                background-color: #c2fbd7;
                border-radius: 100px;
                box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset,rgba(44, 187, 99, .15) 0 1px 2px,rgba(44, 187, 99, .15) 0 2px 4px,rgba(44, 187, 99, .15) 0 4px 8px,rgba(44, 187, 99, .15) 0 8px 16px,rgba(44, 187, 99, .15) 0 16px 32px;
                color: green;
                cursor: pointer;
                display: inline-block;
                font-family: CerebriSans-Regular,-apple-system,system-ui,Roboto,sans-serif;
                padding: 7px 20px;
                text-align: center;
                text-decoration: none;
                transition: all 250ms;
                border: 0;
                font-size: 16px;
                -webkit-user-select: none;
            }

            .button-take:hover {
                box-shadow: rgba(44,187,99,.35) 0 -25px 18px -14px inset,rgba(44,187,99,.25) 0 1px 2px,rgba(44,187,99,.25) 0 2px 4px,rgba(44,187,99,.25) 0 4px 8px,rgba(44,187,99,.25) 0 8px 16px,rgba(44,187,99,.25) 0 16px 32px;
                transform: scale(1.05) rotate(-1deg);
            }

            .button-view {
                background-color: #c2fbd7;
                border-radius: 100px;
                box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset,rgba(44, 187, 99, .15) 0 1px 2px,rgba(44, 187, 99, .15) 0 2px 4px,rgba(44, 187, 99, .15) 0 4px 8px,rgba(44, 187, 99, .15) 0 8px 16px,rgba(44, 187, 99, .15) 0 16px 32px;
                color: green;
                cursor: pointer;
                display: inline-block;
                font-family: CerebriSans-Regular,-apple-system,system-ui,Roboto,sans-serif;
                padding: 7px 20px;
                text-align: center;
                text-decoration: none;
                transition: all 250ms;
                border: 0;
                font-size: 16px;
                -webkit-user-select: none;
            }

            .button-view:hover {
                box-shadow: rgba(44,187,99,.35) 0 -25px 18px -14px inset,rgba(44,187,99,.25) 0 1px 2px,rgba(44,187,99,.25) 0 2px 4px,rgba(44,187,99,.25) 0 4px 8px,rgba(44,187,99,.25) 0 8px 16px,rgba(44,187,99,.25) 0 16px 32px;
                transform: scale(1.05) rotate(-1deg);
            }

            .button-edit {
                background-color: #c2fbd7;
                border-radius: 100px;
                box-shadow: rgba(44, 187, 99, .2) 0 -25px 18px -14px inset,rgba(44, 187, 99, .15) 0 1px 2px,rgba(44, 187, 99, .15) 0 2px 4px,rgba(44, 187, 99, .15) 0 4px 8px,rgba(44, 187, 99, .15) 0 8px 16px,rgba(44, 187, 99, .15) 0 16px 32px;
                color: green;
                cursor: pointer;
                display: inline-block;
                font-family: CerebriSans-Regular,-apple-system,system-ui,Roboto,sans-serif;
                padding: 7px 20px;
                text-align: center;
                text-decoration: none;
                transition: all 250ms;
                border: 0;
                font-size: 16px;
                -webkit-user-select: none;
            }

            .button-edit:hover {
                box-shadow: rgba(44,187,99,.35) 0 -25px 18px -14px inset,rgba(44,187,99,.25) 0 1px 2px,rgba(44,187,99,.25) 0 2px 4px,rgba(44,187,99,.25) 0 4px 8px,rgba(44,187,99,.25) 0 8px 16px,rgba(44,187,99,.25) 0 16px 32px;
                transform: scale(1.05) rotate(-1deg);
            }
             .title-detail{
                margin-top: 5.5rem;
                padding-left: 20.5rem;
                display:flex;
                align-items: center;
                color: #3E54AC;
            }
        </style>
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1 style="font-family: Helvetica,Arial,sans-serif;">FPT University Academic Portal</h1></div>
        <div class="title-detail"><h2 style="font-family: Helvetica,Arial,sans-serif;">List attendance groups for today</h2></div>
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
                            <td>${l.slot.timeFrom}</td>
                            <td>${l.slot.timeTo}</td>
                            <td>${l.group.course.name}</td>
                            <td>${l.group.groupName}</td>
                            <td>${l.room.rname}</td>
                            <c:if test="${l.status eq null}">
                                <td>
                                    <form action="attendance" method="get">
                                        <input type="hidden" name="instructor" value="${l.instructor.instructorId}" >
                                        <input type="hidden" name="groupid" value="${l.group.groupId}" >
                                        <input type="hidden" name="slot" value="${l.slot.slotId}" >
                                        <input type="hidden" name="sessionid" value="${l.id}" >
                                        <input  class="button-take" type="submit" value="Take" >
                                    </form>
                                </td>
                            </c:if>

                            <c:if test="${l.status ne null}">
                                <td style="color: green">
                                    Take attendance successfully <i class="fa-sharp fa-solid fa-circle-check"></i>
                                </td>
                            </c:if>   
                            <c:if test="${l.status ne null}">
                                <td>
                                    <form action="viewattendance" method="post">
                                        <input type="hidden" name="instructor" value="${l.instructor.instructorId}" >
                                        <input type="hidden" name="groupid" value="${l.group.groupId}" >
                                        <input type="hidden" name="slot" value="${l.slot.slotId}" >
                                        <input type="hidden" name="sessionid" value="${l.id}" >
                                        <input class="button-view" type="submit" value="View">
                                    </form>
                                </td>
                            </c:if>
                            <c:if test="${l.status eq null}">
                                <td>

                                </td>
                            </c:if> 
                            <c:if test="${l.status ne null}">
                                <td>
                                    <form action="updateattendance" method="get">
                                        <input type="hidden" name="instructor" value="${l.instructor.instructorId}" >
                                        <input type="hidden" name="groupid" value="${l.group.groupId}" >
                                        <input type="hidden" name="slot" value="${l.slot.slotId}" >
                                        <input type="hidden" name="sessionid" value="${l.id}" >
                                        <input class="button-take" type="submit" value="Edit" >
                                    </form>
                                </td>
                            </c:if>
                            <c:if test="${l.status eq null}">
                                <td>
                                   
                                </td>
                            </c:if> 
                        </tr>
                    </c:forEach>

                    <c:if test="${requestScope.list.size() == 0}">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
