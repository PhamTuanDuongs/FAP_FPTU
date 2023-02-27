<%-- 
    Document   : lecture
    Created on : Feb 27, 2023, 9:26:49 PM
    Author     : duong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule</title>
        <style>
/*                    *{
                            margin: 0;
                            border: 0;
                            padding: 0;
                        }
            
                        .navigation{
                            position: fixed;
                            width: 300px;
                            height:670px;
                            background-color: rgb(91, 91, 207);
                            color: bisque(218, 180, 135);
                            border-radius: 10px;
                            top: 10px;
                            left: 20px;
                            box-sizing: initial;
                            transition: width 0.5;
                        }
                        .navigation ul{
                            position: absolute;
                            top: 0;
                            left: 0;
                            width: 100%;
                            padding-left: 5px;
                            padding-top: 40px;
                        }
                        .navigation ul li{
                            position: relative;
                            list-style: none;
                            width: 100%;
                            border-top-left-radius: 20px;
                            border-bottom-left-radius: 20px;
                        }
            
                        .navigation ul li a {
                            position:relative;
                            display: block;
                            width: 100%;
                            display: flex;
                            text-decoration: none;
                            color: #ffff;
                        }
                        .navigation ul li.active a{
                            color: black;
                        }
                        .list.active{
                            background: #fff;
                        }
                        .navigation ul li a .icon{
                            position: relative;
                            display: block;
                            min-width: 20px;
                            height: 60px;
                            line-height: 60px;
                            text-align: center;
                            padding-right: 30px;
                            margin-left: 20px;
                        }
            
                        .navigation ul li a .icon ion-icon{
                            font-size: 1.5em;
                        }
                        .navigation ul li a .title{
                            position: relative;
                            display: block;
                            padding-left: 10px;
                            height: 60px;
                            line-height: 60px;
                            white-space: normal;
                        }
                        .toggle{
                            position: absolute;
                            bottom: 15px;
                            right: 15px;
                            width: 50px;
                            height: 50px;
                            background: #fff;
                            border-radius: 50%;
                            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.15);
                            cursor: pointer;
                            display: flex;
                            justify-content: center;
                            align-items: center;
                        }
                        .toggle::before
                        {
                            content: '';
                            position: absolute;
                            width: 26px;
                            height: 3px;
                            border-radius: 3px;
                            background: #365fa1;
                            transform: translateY(-5px);
                            transition: 1s;
                        }
                        .toggle::after
                        {
                            content: '';
                            position: absolute;
                            width: 26px;
                            height: 3px;
                            border-radius: 3px;
                            background: #365fa1;
                            transform: translateY(5px);
                            transition: 1s;
                        }
                        
                        .form{
                            margin-top: 100px;
                            margin-left: 350px
                        }*/
                        table,tr,td{
                            border: 1px solid black;
                        }
        </style>
        <!--        <script>
                    const list = document.querySelectorAll('.list');
                    console.log(list);
        // Thiết lập lớp active cho phần tử đầu tiên
                    list[0].classList.add('active');
        
                    function activeLink() {
                        // event.preventDefault();
                        // data-target
                        // Loại bỏ lớp active khỏi tất cả các phần tử
                        list.forEach(item => item.classList.remove('active'));
        
                        // Thêm lớp active vào phần tử hiện tại
                        this.classList.add('active');
                    }
        
        // Đăng ký sự kiện click cho tất cả các phần tử
                    list.forEach(item => item.addEventListener('click', activeLink));
        
                </script>-->
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
                <input type="text" name="rollnumber" /><br>
                <label>From:</label>
                <input type="date" name="dateFrom" />
                <label>To:</label>
                <input type="date" name="dateTo" />
                <input type="submit" value="View" />
            </form>
        </div>

        <table>
            <tr>
                <td>Group</td>
                <td>SlotId</td>
                <td>Code</td>
                <td>Room</td>
                <td>status</td>
            </tr>
            <tr>
                <c:forEach items="${requestScope.schedule}" var="l">
                    <c:if test="${l.slotId eq 1}">  
                        <td>${l.group}${l.slotId}${l.course}${l.room}${l.status}</td>
                    </c:if> 
                </c:forEach>
            </tr>
        </table>

    </body>
</html>
