<%-- 
    Document   : sideBar
    Created on : Mar 4, 2023, 10:08:02 PM
    Author     : duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
        <title>JSP Page</title>
        <style>
            *{
                margin: 0;
                border: 0;
                padding: 0;
            }

            .navigation{
                position: fixed;
                width: 275px;
                height:670px;
                background-color: #645CBB;
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

           
        </style>
    </head>
    <body>
        <div class="navigation">
            <ul>
                <li class="list">
                    <a href="http://localhost:9999/fap/homes">
                        <span class="icon"><i class="fa-solid fa-house"></i></span>
                        <span class="title">Home</span>
                    </a>
                </li>
                <li class="list">
                    <a href="http://localhost:9999/fap/timetable">
                        <span class="icon"><i class="fa-solid fa-calendar"></i></span>
                        <span class="title">Weekly Timetable</span>
                    </a>
                </li>
                <li class="list">
                    <a href="http://localhost:9999/fap/listattendancegroups">
                        <span class="icon"><i class="fa-solid fa-user-check"></i></span>
                        <span class="title">Take Attendance For Today</span>
                    </a>
                </li>
                <li class="list">
                    <a href="http://localhost:9999/fap/logout">
                        <span class="icon"><i class="fa-solid fa-right-from-bracket"></i></span>
                        <span class="title">Log Out</span>
                    </a>
                </li>
            </ul>

            <div class="toggle"></div>
        </div>
    </body>
</html>
