<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Management</title>
</head>
<body>
    <h1>Add series</h1>
    <form method = "post" action = "${pageContext.request.contextPath}/administrative/upload_series" enctype = "multipart/form-data">
        <label>Title:
            <input type = "text" name = "title"><br>
        </label>

        <label>Rating:
            <input type = "text" name = "rating"><br>
        </label>

        <label>Poster:
            <input type = "file" name = "poster" accept="image/*"><br>
        </label>

        <input type = "submit" name = "submit" value = "Submit"><br>
    </form>

    <h1>Add season</h1>
    <form method = "post" action = "${pageContext.request.contextPath}/administrative/upload_season" enctype = "multipart/form-data">
        <label>Title:
            <input type = "text" name = "title"><br>
        </label>

        <label>Release date:
            <input type = "date" name = "release_date"><br>
        </label>

        <label>End date:
            <input type = "date" name = "end_date"><br>
        </label>

        <label>Season number:
            <input type = "number" name = "season_no"><br>
        </label>

        <label>Prequel title:
            <input type = "text" name = "prequel_title"><br>
        </label>

        <label>Sequel title:
            <input type = "text" name = "sequel_title"><br>
        </label>

        <label>Series title:
            <input type = "text" name = "series_title"><br>
        </label>

        <label>Poster:
            <input type = "file" name = "poster" accept = "image/*"><br>
        </label>

        <input type = "submit" name = "submit" value = "Submit"><br>
    </form>

</body>
</html>
