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
        <title>Register</title>
    </head>
    <body>
    <h1>Create an account</h1><br>
        <form action = "register" method = "post">
            <table>
                <!-- First name -->
                <tr>
                    <td><input type = "text" id = "fname" name = "fname" placeholder = "First name" maxlength = "30" autofocus></td>
                </tr>

                <!-- Last name -->
                <tr>
                    <td><input type = "text" id = "lname" name = "lname" placeholder = "Last name" maxlength = "30"></td>
                </tr>

                <!-- Email -->
                <tr>
                    <td><input type = "email" id = "email" name = "email" placeholder = "Email" maxlength = "40"></td>
                </tr>

                <!-- Birth date -->
                <tr>
                    <td><input type = "date" id = "bdate" name = "bdate"></td>
                </tr>

                <!-- Username -->
                <tr>
                    <td><input type = "text" id = "username" name = "username" placeholder = "Username" maxlength = "25"></td>
                </tr>

                <!-- Password -->
                <tr>
                    <td><input type = "password" id = "password" name = "password" placeholder = "Password" maxlength = "40"></td>
                </tr>

                <!-- Confirm password -->
                <tr>
                    <td><input type = "password" id = "cpassword" name = "cpassword" placeholder = "Confirm password" maxlength = "40"></td>
                </tr>

                <!-- Register button -->
                <tr>
                    <td><input type = "submit" value = "Register" name = "register"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
