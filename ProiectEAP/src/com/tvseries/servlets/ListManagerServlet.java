package com.tvseries.servlets;

import com.tvseries.dao.Seasons_ListDAO;
import com.tvseries.tables.SeasonListItem;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ListManagerServlet extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        HttpSession session = req.getSession();

        String status = req.getParameter("status");
        if(status.equals("plan"))
        {
            status = "plan to watch";
        }

        try
        {
            ArrayList<SeasonListItem> seasons = Seasons_ListDAO.getSeasonList((String)session.getAttribute("username"), status);

            req.setAttribute("list", seasons);
            req.getRequestDispatcher("/restricted/list.jsp").forward(req, res);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
