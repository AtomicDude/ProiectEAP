package com.tvseries.servlets;

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
            //TODO: modificari
            //ArrayList<ListItem> seasons = Seasons_ListDAO.getSeasonList((String)session.getAttribute("username"), status);

            //req.setAttribute("list", seasons);
            req.getRequestDispatcher("/restricted/list.jsp").forward(req, res);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        HttpSession session = req.getSession();

        String username = (String)session.getAttribute("username");

        try
        {
            if (username != null)
            {
                String watch_status = req.getParameter("watch_status");
                int current_ep = Integer.parseInt(req.getParameter("current_ep"));

                if(watch_status.equals("Not watching"))
                {
                    //add or update season_list
                }
                else
                {

                }
            }
            else
            {
                res.sendRedirect(req.getContextPath() + "/login.jsp");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
