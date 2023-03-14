<%-- 
    Document   : home
    Created on : Mar 14, 2023, 10:51:00 AM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <style>
            body{
               background-color: #EEEEEE;
            }
            .content{
                color: #3E54AC;
                width: 60rem;
                height: 42rem;
                margin: auto;
                margin-left: 22rem;
                text-align: center;
                margin-top: 1rem;
                line-height: 3rem;
            }
        </style>
    </head>
    <body>
        <%@ include file = "sideBar.jsp" %>
        <div class="content">
        <h2>Hello ${requestScope.profile}</h2>
        <h1>STUDENT</h1>
        </div>
    </body>
</html>
