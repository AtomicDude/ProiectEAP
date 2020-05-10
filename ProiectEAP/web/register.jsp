<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "styles/general_style.css">
        <link rel = "stylesheet" type = "text/css" href = "styles/hnavbar_style.css">
        <link rel = "stylesheet" type = "text/css" href = "styles/list_style.css">
        <link rel = "stylesheet" type = "text/css" href = "styles/dropdown_style.css">
        <title>Register</title>
    </head>
    <body>
        <jsp:include page = "home/header.jsp"></jsp:include>
        <jsp:include page = "home/hnavbar.jsp"></jsp:include>

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

                <!-- Display name -->
                <tr>
                    <td><input type = "text" id = "display_name" name = "display_name" placeholder = "Display name" maxlength = "25"></td>
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
