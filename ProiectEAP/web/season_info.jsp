<%@ page import="com.tvseries.containers.SeasonInfoContainer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.tvseries.utils.Triplet" %>
<%@ page import="javafx.util.Pair" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    SeasonInfoContainer season_info = (SeasonInfoContainer)request.getAttribute("season_info");
    Triplet<Integer, String, String> prequel = season_info.getPrequel();
    Triplet<Integer, String, String> sequel = season_info.getSequel();
    Triplet<Integer, String, String> series = season_info.getSeries();
    List<Pair<Integer, String>> episode = season_info.getEpisodes();

    List<String> watch_status = new ArrayList<>();
    watch_status.add("Watching");
    watch_status.add("Completed");
    watch_status.add("Dropped");
    watch_status.add("Plan to watch");
    watch_status.remove(season_info.getWatch_status()); //remove the watch status of the user
%>

<!DOCTYPE html>
<html>
    <head>
        <link rel = "stylesheet" type = "text/css" href = "styles/general_style.css">
        <link rel = "stylesheet" type = "text/css" href = "styles/hnavbar_style.css">
        <link rel = "stylesheet" type = "text/css" href = "styles/dropdown_style.css">
        <title><%=season_info.getSeason_title()%></title>
    </head>
    <body>
        <jsp:include page = "home/header.jsp"></jsp:include>
        <jsp:include page = "home/hnavbar.jsp"></jsp:include>

        <p><%=season_info.getSeason_title()%></p><br>
        <img src = "<%=request.getContextPath() + season_info.getSeason_poster()%>" alt = "poster" width = "400" height = "400"><br>
        <p>Season number: <%=season_info.getSeason_no()%></p><br>

        <form method = "post" action = "update_list">
            <label>
                Status:
                <select name = "watch_status">
                    <option value = "<%=season_info.getWatch_status()%>" selected><%=season_info.getWatch_status() == null ? "Not watching" : season_info.getWatch_status()%></option>
                    <%
                        for(String ws : watch_status)
                        {
                    %>
                            <option value = "<%=ws%>"><%=ws%></option>
                    <%
                        }
                    %>
                </select>
            </label>

            <label>
                Current ep:
                <input type = "number" name = "current_ep" value = "<%=season_info.getCurrent_ep() == null ? 0 : season_info.getCurrent_ep()%>" maxlength = "3">
            </label>

            <input type = "submit" value = "Update">
        </form>

    </body>
</html>