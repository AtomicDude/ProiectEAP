<%--
  Created by IntelliJ IDEA.
  User: Joe
  Date: 05/05/2020
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1><br>
        <form action = "login" method = "post">
            <table>
                <!-- Username -->
                <tr>
                    <td><input type = "text" id = "username" name = "username" placeholder = "Username"></td>
                </tr>

                <!-- Password -->
                <tr>
                    <td><input type = "password" id = "password" name = "password" placeholder = "Password"></td>
                </tr>

                <!-- Login button -->
                <tr>
                    <td><input type = "submit" value = "Login" name = "login"></td>
                </tr>

                <!-- Login message -->
                <tr>
                    <td>${login_message}</td>
                </tr>
            </table>
        </form>
    </body>
</html>
