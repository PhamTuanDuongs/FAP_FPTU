<%-- 
    Document   : timetable
    Created on : Mar 4, 2023, 9:12:55 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <style>
            table{
                border: 1px solid black;
                width: 70rem;
                height: 30rem;
                border-collapse: collapse;
            }
            th{
                height: 30px;
                text-align: left;
                border-right: 1px solid gray;
            }
            thead{
                background-color: #8077e0;
            }
            thead,tbody,tr,td{
                border: 1px solid gray;
            }
            .timetable{
                width: 70rem;
                margin-left:320px;
            }

            .title{
                padding-left: 20rem;
                height: 100px;
                display:flex;
                align-items: center;
                font-family: Helvetica,Arial,sans-serif;
            }
            .title-detail{
                padding-left: 20rem;
                height: 70px;
                display:flex;
                align-items: center;
                color: #3E54AC;
                font-family: Helvetica,Arial,sans-serif;
            }
        </style> 

    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="title"><h1>FPT University Academic Portal</h1></div>
         <div class="title-detail"><h1>Timetable</h1></div>
        <div class="timetable">
            <table>
                <form action="timetable" method="get" id="formSubmit">
                    <thead>
                        <tr>
                            <th>
                                Year
                                <select name="year" id="years"  onchange="formSubmitYear()" >
                                    <c:set var="yearC" value="${requestScope.yearCurrent}"/>
                                    <c:forEach items="${requestScope.listYear}" var="year">
                                        <option value="${year}" ${year eq yearC ? 'selected' : ''}>${year}</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th>Mon</th>
                            <th>Tues</th>
                            <th>Weds</th>
                            <th>Thurs</th>
                            <th>Fri</th>
                            <th>Sun</th>
                            <th>Sat</th>
                        </tr>
                        <tr>
                            <th>
                                Week:
                                <select name="week" onchange="formSubmit()" >
                                    <c:set var="t" value="0"/>
                                    <c:set var="currentweek" value="${requestScope.current}"/>
                                    <c:forEach items="${requestScope.list}" var="week">
                                        <c:set var="t" value="${t+1}"/>
                                        <option value="${t}" ${ t eq currentweek ? 'selected':''}>${week}</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <c:forEach items="${requestScope.days}" var="d">
                                <th>${d}</th>
                            </c:forEach>
                        </tr>

                    </thead>
                </form>
                <tbody>
                    <c:forEach items="${requestScope.slots}" var="slot" > 
                        <tr>
                            <td style="width: 222px">Slot ${slot.slotId}<br><span style="background-color: #BFACE2">${slot.timeFrom}-${slot.timeTo}</span></td>
                            <c:forEach begin="2" end="8" step="1" varStatus="w">
                                <td>
                                    <c:forEach items="${requestScope.schedule}" var="l">
                                        <c:set var="week" value="${l.weekday}"/>
                                        <c:if test="${l.slot.slotId eq slot.slotId}">  
                                            <c:if test="${l.weekday eq w.index}">  
                                                ${l.group.groupName}<br>
                                                ${l.group.course.code}<br>
                                                ${l.room.rname}<br>
                                                <c:set var="t" value="${l.status}"/>
                                                <span ${t eq null || t eq  'absent' ? 'style="color: red"': 'style="color: green" '}>(${t eq null ? 'not yet': t eq "absent" ? 'absent' : t})</span> 
                                                <form action="statuss" method="get">
                                                    <input type="hidden" name="groupid" value="${l.group.groupId}" >
                                                    <input type="submit" value="Status" >
                                                </form>
                                            </c:if>
                                        </c:if> 
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script>
            function formSubmit() {
                document.getElementById("formSubmit").submit();
            }

            function formSubmitYear() {
                var year = document.getElementById("years")
                if (year.value !== 2023) {
                    window.location.href = "http://localhost:9999/fap/timetable?year=" + year.value + "&week=1";
                }
            }
        </script>
    </body>
</html>
