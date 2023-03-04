<%-- 
    Document   : lecture
    Created on : Feb 27, 2023, 9:26:49 PM
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
            
        </style> 
        <script>
            var url = "http://localhost:9999/fap/schedule";
            function year() {
                var y = document.getElementById("years").value;
                if (y !== null) {
                    url = url+"?year="+y;
                    window.location = url;

                }
            }
            
            function week() {
                var w = document.getElementById("weeks").value;
                if (w !== null) {
                    window.location = url + "?week=" + w;
                }
            }


        </script>
    </head>
    <body>
        <!--        <div class="navigation">
                    <ul>
                        <li class="list active">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-user"></i></span>
                                <span class="title">User</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-calendar"></i></span>
                                <span class="title">Weekly Timetable</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-user-check"></i></span>
                                <span class="title">Attendance Report</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-clipboard-user"></i></span>
                                <span class="title">Take attendance</span>
                            </a>
                        </li>
                        <li class="list">
                            <a href="#">
                                <span class="icon"><i class="fa-solid fa-right-from-bracket"></i></span>
                                <span class="title">Sign Out</span>
                            </a>
                        </li>
                    </ul>
        
                    <div class="toggle"></div>
                </div>-->
        <div class="form">
            <form action="schedule" method="POST">
                <label>Lecturer</label>
                <input type="hidden" name="std" value="${sessionScope.user.studentId}">
                <label>From:</label>
                <input type="date" name="dateFrom" />
                <label>To:</label>
                <input type="date" name="dateTo" />
                <input type="submit" value="View" />
            </form>

        </div>

        <table>
            <thead>

                <tr>
                    <td>
                        Year
                        <select id="years"  onchange="year()" >
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
                        <select id="weeks" onchange="week()"   >
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
            <tbody>
                <c:forEach begin="1" end="4" step="1" varStatus="i"> 
                    <tr>
                        <td>Slot ${i.index}</td>
                        <c:forEach begin="2" end="8" step="1" varStatus="w">
                            <td>
                                <c:forEach items="${requestScope.schedule}" var="l">
                                    <c:set var="week" value="${l.weekDay}"/>
                                    <c:if test="${l.slot eq i.index}">  
                                        <c:if test="${l.weekDay eq w.index}">  
                                            ${l.groupName}<br>
                                            ${l.course}<br>
                                            ${l.rname}<br>
                                            
                                             <c:set var="t" value="${l.status}"/>
                                             <span ${t eq 'not yet' || t eq  'absent' ? 'style="color: red"': 'style="color: green" '} >(${l.status})</span> 
                                        </c:if>
                                    </c:if> 
                                </c:forEach>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>


            </tbody>
        </table>
    </body>
</html>
