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
            }
            thead{
                background-color: #655DBB;
            }
            thead,tbody,tr,td{
                border: 1px solid black;
            }
            .timetable{
                width: 70rem;
                margin-left:300px;
                margin-top:100px;
                border: 1px solid red;
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
        <div class="title"><h1>FPT University Academic Portal</h1></div>
        <div class="timetable">
            <table>
                <form action="timetable" method="get" id="formSubmit">
                    <thead>
                        <tr>
                            <td>
                                Year
                                <select name="year" id="years"  onchange="formSubmitYear()" >
                                    <c:set var="yearC" value="${requestScope.yearCurrent}"/>
                                    <c:forEach items="${requestScope.listYear}" var="year">
                                        <option value="${year}" ${year eq yearC ? 'selected' : ''}>${year}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>Mon</td>
                            <td>Tues</td>
                            <td>Weds</td>
                            <td>Thurs</td>
                            <td>Fri</td>
                            <td>Sun</td>
                            <td>Sat</td>
                        </tr>
                        <tr>
                            <td>
                                Week:
                                <select name="week" onchange="formSubmit()" >
                                    <c:set var="t" value="0"/>
                                    <c:set var="currentweek" value="${requestScope.current}"/>
                                    <c:forEach items="${requestScope.list}" var="week">
                                        <c:set var="t" value="${t+1}"/>
                                        <option value="${t}" ${ t eq currentweek ? 'selected':''}>${week}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <c:forEach items="${requestScope.days}" var="d">
                                <td>${d}</td>
                            </c:forEach>
                        </tr>

                    </thead>
                </form>
                <tbody>
                    <c:forEach items="${requestScope.slots}" var="slot" > 
                        <tr>
                            <td>Slot ${slot.slotId}</td>
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
