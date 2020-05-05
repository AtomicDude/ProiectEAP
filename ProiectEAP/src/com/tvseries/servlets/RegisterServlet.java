package com.tvseries.servlets;

import com.tvseries.dao.UserDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.*;
import java.time.LocalDate;

import org.apache.commons.codec.digest.DigestUtils;

public class RegisterServlet extends HttpServlet
{
    public boolean validatePassword(String pass, String cpass)
    {
        return pass.equals(cpass);
    }

    public String processPassword(String pass)
    {
        String hashpass = DigestUtils.sha256Hex(pass.getBytes());

        return hashpass;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String cpassword = req.getParameter("cpassword");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String bdates = req.getParameter("bdate");

        LocalDate bdate = LocalDate.parse(bdates, DateTimeFormatter.ISO_LOCAL_DATE);

        if(validatePassword(password, cpassword))
        {
            String hashpass = processPassword(password);

            try
            {
                int lines = UserDAO.addUser(username, email, hashpass, fname, lname, bdate);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }


    }
}
