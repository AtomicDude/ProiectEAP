package com.tvseries.servlets;

import com.tvseries.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.tvseries.utils.PasswordChecker;
import org.apache.commons.codec.digest.DigestUtils;

public class RegisterServlet extends HttpServlet
{
    //TODO: validate email, check if username/email is unique
    public boolean validatePassword(String pass, String cpass)
    {
        return (pass != null && pass.length() >= 10 && pass.length() <= 40 && pass.equals(cpass) && PasswordChecker.check(pass));
    }

    public boolean validateName(String name)
    {
        return (name != null && name.length() >= 4 && name.length() <= 30 && PasswordChecker.check(name));
    }

    public boolean validateEmail(String email)
    {
        return (email != null);
    }

    public String processPassword(String pass)
    {
        String hashpass = DigestUtils.sha256Hex(pass.getBytes());

        return hashpass;
    }

    public Map<String, String> validateCredentials(String user, String disp, String email, String pass, String cpass)
    {
        Map<String, String> messages = new HashMap<String, String>();

        if(!validateName(user))
        {
            messages.put("username", "Invalid username");
        }

        if(!validateName(disp))
        {
            messages.put("display", "Invalid display name");
        }

        if(!validateEmail(email))
        {
            messages.put("email", "Invalid email");
        }

        if(!validatePassword(pass, cpass))
        {
            messages.put("password", "Invalid password");
        }

        return messages;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String username = req.getParameter("username");
        String display_name = req.getParameter("display_name");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String cpassword = req.getParameter("cpass");
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String bdates = req.getParameter("bdate");

        if(bdates == null || bdates.equals(""))
        {
            bdates = "1900-01-01";
        }

        LocalDate bdate = LocalDate.parse(bdates, DateTimeFormatter.ISO_LOCAL_DATE);

        Map<String, String> messages = validateCredentials(username, display_name, email, password, cpassword);

        HttpSession session = req.getSession();

        if(messages.isEmpty()) //if there are no error messages
        {
            String hashpass = processPassword(password);

            try
            {
                int lines = UserDAO.addUser(username, display_name, email, hashpass, fname, lname, bdate);
                session.setAttribute("username", username);
                session.setAttribute("display_name", display_name);
                res.sendRedirect(req.getContextPath() + "/index.jsp");

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            req.setAttribute("messages", messages); //send error messages
            req.getRequestDispatcher("/register.jsp").forward(req, res);
        }
    }
}
