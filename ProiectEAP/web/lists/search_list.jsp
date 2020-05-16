<%@ page import="com.tvseries.dao.SeriesDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tvseries.tables.Series" %>
<!-- A list containing the search results -->

<%
    ArrayList<Series> series = null;
    try
    {
        series = SeriesDAO.searchSeries(request.getParameter("search_key"));
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }


    if(series != null && series.size() > 0)
    {
        for(Series s : series)
        {
%>
            <p><%=s.getTitle()%></p>
<%
        }
    }
    else
    {
%>
        <p>No results</p>
<%
    }
%>

