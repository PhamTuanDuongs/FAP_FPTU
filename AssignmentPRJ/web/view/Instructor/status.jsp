<%-- 
    Document   : status
    Created on : Mar 14, 2023, 3:27:02 PM
    Author     : duong
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Status Page</title>
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
            table{
                height: 25rem;
            }
            .timetable{
                width: 90rem;
                height: 50rem;

            }
            
        </style>
    </head>
    <body>
        <h1>Status</h1>
        <div  class="timetable">
            <table>
                <thead>
                <th style="width: 2rem">NO</th>
                <th style="width: 4rem">Group</th>
                <th style="width: 4rem">RollNumber</th>
                <th style="width: 10rem">Name</th>
                    <c:forEach var = "i" begin = "1" end = "20">
                    <th>slot ${i}</th>
                    </c:forEach>
                <th style="width: 10rem">ABSENT <= 20</th>    
                </thead>
                <tbody>
                    <c:forEach items="${requestScope.list}" var="l" varStatus="loop">

                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${l.group.groupName}</td>
                            <td class="roll">${l.rollnumber}</td>
                            <td>${l.lastName}${""}${l.firstName}</td>

                            <c:set var="p" value="0"/>
                            <c:forEach items="${listStudent}" var="ls">
                                <c:if test="${l.studentid == ls.student.studentid}">
                                    <td> <c:set var="t" value="${ls.status}"/>
                                        <span ${t eq  "absent" ? 'style="color: red"': t eq  "attended" ? 'style="color: green"': 'style="color: black"'}> ${ls.status eq null ? 'F': ls.status eq "attended" ? 'P' : ls.status eq "absent" ? 'A':''}</span></td>
                                        <c:if test="${ls.status eq 'absent'}">
                                            <c:set var="p" value="${p+1}"/>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <fmt:formatNumber var="aa" value="${p/20*100}" pattern="##"/>
                            <td ${aa >= 10 ? 'style="color:red"':''} >${aa}%</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>


<c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
    <c:if test="${a.status eq 'absent'}">
        <c:set var="p" value="${p+1}"/>
    </c:if>
</c:forEach>

