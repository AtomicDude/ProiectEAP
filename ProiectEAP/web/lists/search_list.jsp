<%@ page import="com.tvseries.dao.SeriesDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tvseries.tables.Series" %>
<%@ page import="com.tvseries.tables.Season" %>
<%@ page import="com.tvseries.dao.SeasonDAO" %>
<!-- A list containing the search results -->

<%
    ArrayList<Series> series = null;
    ArrayList<Season> seasons = null;

    try
    {
        series = SeriesDAO.searchSeries(request.getParameter("search_key"), 10);
        seasons = SeasonDAO.searchSeason(request.getParameter("search_key"), 10);
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }


    if(seasons != null && seasons.size() > 0)
    {
        for(Season s : seasons)
        {
%>
            <a href = "<%=request.getContextPath() + "/season_info?season_id=" + s.getSeason_id()%>">
                <%=s.getTitle()%>
            </a>
<%
        }
    }
%>

<%
    if(series != null && series.size() > 0)
    {
        for(Series s : series)
        {
%>
            <p><%=s.getTitle()%></p>
<%
        }
    }
%>

<%
    if(series == null && seasons == null)
    {
%>
        <p>No results</p>
<%
    }
%>

