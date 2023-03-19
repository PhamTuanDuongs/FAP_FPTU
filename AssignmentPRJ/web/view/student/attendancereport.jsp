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
            body{
                background-color: #EEEEEE;
            }
            table,thead,th,tbody,tr,td{
                border: 1px solid black;
            }
            table{
                height: 70%;
                border: 1px solid black;
                border-collapse: collapse;
            }
            thead{
                background-color: #837be1;
            }
            th{
                width: 300px;
                height: 50px;
            }
            .timetable{
                width: 68rem;
                height:100rem;
                margin-left:320px;
                margin-top:30px;
            }
            .formm{
                width: 700px;
                margin-left:320px;
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
                padding-left: 19.7rem;
                height: 100px;
                display:flex;
                align-items: center;
                font-family: Helvetica,Arial,sans-serif;
            }
            .title-detail{
                padding-left: 19.7rem;
                height: 50px;
                display:flex;
                align-items: center;
                font-family: Helvetica,Arial,sans-serif;
                color: #3E54AC;
            }
            .percentage{
                text-align: right;
                font-weight: bold;
            }

            .button-66 {
                background-color: #0a6bff;
                border-radius: 4px;
                border: 0;
                box-shadow: rgba(1,60,136,.5) 0 -1px 3px 0 inset,rgba(0,44,97,.1) 0 3px 6px 0;
                box-sizing: border-box;
                color: #fff;
                cursor: pointer;
                font-family: "Space Grotesk",-apple-system,system-ui,"Segoe UI",Roboto,Helvetica,Arial,sans-serif,"Apple Color Emoji","Segoe UI Emoji","Segoe UI Symbol";
                font-size: 18px;
                font-weight: 700;
                margin: 0;
                min-height: 36px;
                min-width: 90px;
                position: relative;
                text-align: center;
                user-select: none;
                -webkit-user-select: none;
                touch-action: manipulation;
                vertical-align: baseline;
                transition: all .2s cubic-bezier(.22, .61, .36, 1);
            }

            .button-66:hover {
                background-color: #065dd8;
                transform: translateY(-2px);
            }

        </style>
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1>FPT University Academic Portal</h1></div>        
        <div class="title-detail"><h2>View attendance for ${sessionScope.user.displayname}</h2></div>        
        <div class="formm">
            <form action="viewattendstudent" method="GET">
                <input type="hidden" name="studenId" value="${sessionScope.user.studentId}">
                <h3>Choose the course:</h3> 
                <select style="height: 30px" name="courseId">
                    <c:forEach items="${requestScope.course}" var="c">
                        <c:set var="course" value="${requestScope.courseid}"/>
                        <option value="${c.course.courseId}"  ${course == c.course.courseId ? 'selected':''}   >${c.groupName}(${c.course.name})</option>
                    </c:forEach>
                </select> 
                <input class="button-66" type="submit" value="View" />
            </form>
        </div>

        <c:if test="${requestScope.attendance ne null}">

            <div class="timetable">
                <c:set var="p" value="0"/>
                <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                    <c:if test="${a.status eq 'absent'}">
                        <c:set var="p" value="${p+1}"/>
                    </c:if>
                </c:forEach>
                <c:set var="size" value="${requestScope.attendance.size()}"/>
                <fmt:formatNumber var="aa" value="${p/size*100}" pattern="##"/>
                <p class="percentage">ABSENT: ${aa}% ABSENT SO FAR ( ${p} ABSENT ON ${size} TOTAL).</p>
                <table>
                    <thead>
                    <th>NO</th>
                    <th>DATE</th>   
                    <th>SLOT</th>
                    <th>ROOM</th>
                    <th>LECTURER</th>
                    <th>GROUP NAME</th>
                    <th>ATTEDANCE STATUS</th>
                    <th>LECTURER'S COMMENT</th>
                    </thead>
                    <tbody>
                        <c:set var="t" value="0"/>
                        <c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
                            <tr>
                                <td style="width: 50px">${loop.index+1}</td>
                                <td style="width: 600px"><fmt:formatDate value="${a.session.date}" pattern="EEEE dd/MMMM/yyyy" /></td>
                                <td style="width: 500px">${a.session.slot.slotId}_(${a.session.slot.timeFrom}-${a.session.slot.timeTo})</td>
                                <td>${a.session.room.rname}</td>
                                <td>${a.session.instructor.instrnumber}</td>
                                <td>${a.session.group.groupName}</td>

                                <td>
                                    <c:set var="t" value="${a.status}"/>
                                    <span ${t eq  "absent" ? 'style="color: red"': t eq  "attended" ? 'style="color: green"': 'style="color: black"'}> ${a.status eq null ? 'Future': a.status eq "attended" ? 'present' : a.status}</span>
                                </td>
                                <td>${a.comment}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </body>
</html>
