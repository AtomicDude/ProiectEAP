package com.tvseries.test;

import com.tvseries.dao.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        LocalDate bdate = LocalDate.parse("1999-03-04", DateTimeFormatter.ISO_LOCAL_DATE);
        int lines = UserDAO.addUser("bog", "adahhj@gmail.com", "pass", "bog", "hr", bdate);

        System.out.println("lines:" + lines);
    }
}
