<%@ page import="com.tvseries.tables.ListItem" %>
<%@ page import="java.util.ArrayList" %>
<!-- A list displayed in the main section of the list.jsp -->

<table class = "ltable">
    <tr>
        <th>No</th>
        <th>Title</th>
        <th>Season number</th>
        <th>Episodes watched</th>
        <th>Score</th>
    </tr>
    <%
        ArrayList<ListItem> seasons = (ArrayList<ListItem>)request.getAttribute("list");

        if(seasons.size() > 0)
        {
    %>
    <%
            int index = 1;
            for(ListItem s : seasons)
            {
    %>
                <tr>
                    <td><%=index++%></td>
                    <td><%=s.getTitle()%></td>
                    <td><%=s.getSeason_no()%></td>
                    <td><%=s.getCurrent_ep()%></td>
                    <td><%=s.getScore()%></td>
                </tr>
    <%
            }

        }
        else
        {
    %>
            <tr><span>The list is empty</span></tr>
    <%
        }
    %>
</table>