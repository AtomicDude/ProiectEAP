package com.tvseries.servlets;

import com.tvseries.dao.UserDAO;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet
{
    public boolean validateCredentials(String user, String pass) throws Exception
    {
        String hashpass = DigestUtils.sha256Hex(pass);
        String db_hashpass = UserDAO.getPassword(user);

        return hashpass.equals(db_hashpass);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = res.getWriter();
        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        try
        {
            if(validateCredentials(user, pass))
            {
                HttpSession session = req.getSession();
                session.setAttribute("username", user);
                res.sendRedirect("welcome.jsp");
            }
            else
            {
                req.setAttribute("login_message", "Invalid username or password!");
                req.getRequestDispatcher("login.jsp").forward(req, res);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
