<%-- 
    Document   : login
    Created on : Feb 22, 2023, 4:25:25 PM
    Author     : duong
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://apis.google.com/js/platform.js"></script>
        <style>
            *{
                margin: 0;
                padding: 0;
                border: 0;
            }

            body {
                background-color: lightgray;
            }

            h1 {
                margin-top: 20px;
                margin-left: 20px;
            }
            .button-login {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                height: 250px;
            }
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                width: 100%;
                height: 600px;
            }
            .login {
                width: 350px;
                height: 500px;
                background-color: #ffff;
                margin: 0;
            }
            .powerd {
                margin-top: 130px;
                text-align: center;
            }
            .powerd a {
                text-decoration: none;
                cursor: pointer
            }
            .powerd a:hover {
                text-decoration: underline;
            }
            .input{
                width: 250px;
                height: 45px;
                margin-top: 40px;
                border-bottom: 1px solid gray;
            }
            .submit{
                width: 250px;
                height: 40px;
                background-color: rgb(107,101,191);
                margin-top: 40px;
                transition: box-shadow .3s;
            }
            .submit:hover{
                box-shadow: 0 0 11px rgba(33,33,33,.2);
                cursor: pointer;
            }
        </style>
        <title>Document</title>
    </head>

    <body>
        <h1>FPT University Academic Portal</h1>
        <div class="container">
            <div class="login">
                <div class="image">
                    <img style="width: 100%; height: 100px;" src="../img/logo.png" alt="">
                </div>
                <div class="button-login">
                    <form action="/fap/login" method="POST">
                        <input class="input" type="text" name="user" placeholder="Email" ></br>
                        <input class="input" type="text" name="pass" placeholder="Password" ></br> 
                        <input class="submit" type="submit" value="Login">
                    </form>
                </div>
                <div class="powerd">
                    <p>© Powered by <a href="https://fpt.edu.vn/" target="_blank">FPT University</a></p>
                </div>
            </div>
        </div>
    </body>
</html>
