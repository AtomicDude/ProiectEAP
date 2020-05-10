package com.tvseries.servlets;

import com.tvseries.dao.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggingServlet extends HttpServlet
{
    public boolean validateCredentials(String user, String pass) throws Exception
    {
        String hashpass = DigestUtils.sha256Hex(pass);
        String db_hashpass = UserDAO.getPassword(user);

        return hashpass.equals(db_hashpass);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        HttpSession session = req.getSession();

        String username = (String)session.getAttribute("username");

        try
        {
            if(username != null) //logout
            {
                session.setAttribute("username", null);
                res.sendRedirect(req.getContextPath() + "/index.jsp");
            }
            else //login
            {
                String user = req.getParameter("username");
                String pass = req.getParameter("password");

                if (validateCredentials(user, pass))
                {
                    session.setAttribute("username", user);
                    res.sendRedirect(req.getContextPath() + "/index.jsp");
                }
                else //invalid credentials
                {

                    req.setAttribute("login_message", "Invalid username or password!");
                    req.getRequestDispatcher("/login.jsp").forward(req, res);
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
