package com.tvseries.test;

import com.tvseries.dao.*;
import com.tvseries.utils.PasswordChecker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        boolean value = PasswordChecker.check("asdf1234!@#$%^&*");

        if(value)
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }
    }
}
