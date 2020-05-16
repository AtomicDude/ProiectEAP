<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel = "stylesheet" type = "text/css" href = "../styles/general_style.css">
    <link rel = "stylesheet" type = "text/css" href = "../styles/hnavbar_style.css">
    <link rel = "stylesheet" type = "text/css" href = "../styles/table_style.css">
    <link rel = "stylesheet" type = "text/css" href = "../styles/dropdown_style.css">
    <title>MyList</title>
</head>
<body>
    <jsp:include page = "../home/header.jsp"></jsp:include>
    <jsp:include page = "../home/hnavbar.jsp"></jsp:include>

    <div class = "row">
        <div class = "col25">

        </div>

        <div class = "col50" style="background-color: aliceblue">
            <jsp:include page = "../lists/mylist.jsp"></jsp:include>
        </div>

        <div class = "col25">

        </div>
    </div>
</body>
</html>
