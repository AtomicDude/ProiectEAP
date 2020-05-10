<%@ page import="com.tvseries.tables.SeasonListItem" %>
<%@ page import="java.util.ArrayList" %>
<!-- A list displayed in the main section of the list.jsp -->
<div class = "lbody">
    <%
        ArrayList<SeasonListItem> seasons = (ArrayList<SeasonListItem>)request.getAttribute("list");

        if(seasons.size() > 0)
        {
            int index = 1;
            for(SeasonListItem s : seasons)
            {
    %>
                <div class = "lrow">
                    <div class = "litem"><%=index++%></div>
                    <div class = "litem"><%=s.getTitle()%></div>
                    <div class = "litem"><%=s.getSeason_no()%></div>
                    <div class = "litem"><%=s.getCurrent_ep()%></div>
                    <div class = "litem"><%=s.getScore()%></div>
                </div>
    <%
            }

        }
        else
        {
    %>
            <div class = "lrow">
                <div class = "litem"><span>The list is empty</span></div>
            </div>
    <%
        }
    %>
</div>

