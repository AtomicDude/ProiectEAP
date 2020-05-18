<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "../styles/general_style.css">
        <link rel = "stylesheet" type = "text/css" href = "../styles/hnavbar_style.css">
        <link rel = "stylesheet" type = "text/css" href = "../styles/dropdown_style.css">
        <title>Title</title>
    </head>
    <body>
        <jsp:include page = "../home/header.jsp"></jsp:include>
        <jsp:include page = "../home/hnavbar.jsp"></jsp:include>

        <img src = "<%=request.getContextPath() + (String)session.getAttribute("picture_path")%>" alt = "profile picture" width = "400" height = "400">

    </body>
</html>
