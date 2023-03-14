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
                height: 24rem;
            }
            .statustable{
                width: 87rem;
                height: 50rem;
                margin-top: 8rem;
            }
            .button{
                text-align: center;
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
        <div class="title"><h1>FPT University Academic Portal</h1></div>  
        <div  class="statustable">
            <table>
                <thead>
                <th style="width: 2rem">NO</th>
                <th style="width: 4rem">Group</th>
                <th style="width: 4rem">RollNumber</th>
                <th style="width: 10rem">Name</th>
                    <c:forEach var = "i" begin = "1" end = "20">
                    <th>slot ${i}</th>
                    </c:forEach>
                <th style="width: 5rem">ABSENT <= 20</th>    
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
            <div class="button"><a class="button-5" href="http://localhost:9999/fap/listattendancegroups">BACK</a></div>
        </div>
    </body>
</html>


<c:forEach items="${requestScope.attendance}" var="a" varStatus="loop">
    <c:if test="${a.status eq 'absent'}">
        <c:set var="p" value="${p+1}"/>
    </c:if>
</c:forEach>

