package com.tvseries.test;

import com.tvseries.dao.*;
import com.tvseries.utils.PasswordChecker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        String name = "`~!@#$%^&*()_-.,/<>?;:'[{}]|";
        System.out.println(name);
        boolean value = (name != null && (name.length() >= 4 && name.length() <= 30) && PasswordChecker.check(name));

        if(value)
        {
            System.out.println("Valid");
        }
        else
        {
            System.out.println("Invalid");
        }
    }
}
