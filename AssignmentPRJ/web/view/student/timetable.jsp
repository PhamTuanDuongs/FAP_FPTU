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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <title>Schedule</title>
        <style>
            table{
                border: 1px solid black;
                width: 70rem;
                height: 30rem;
            }
            thead{
                background-color: #3E54AC;
            }
            th{
                height:30px;
                text-align: left;
            }
            thead,tbody,tr,td{
                border: 1px solid black;
            }

            *{
                margin: 0;
                border: 0;
                padding: 0;
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
                <thead>
                <form action="schedule" method="GET" id="formSubmit" >
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
                </form>

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
        </div>
        <script>
            function formSubmit() {
                document.getElementById("formSubmit").submit();
            }
            
            function formSubmitYear(){
                var year = document.getElementById("years")
                 if(year.value !== 2023){
                      window.location.href = "http://localhost:9999/fap/schedule?year="+year.value+"&week=1";
                 }
            }
        </script>
    </body>
</html>
