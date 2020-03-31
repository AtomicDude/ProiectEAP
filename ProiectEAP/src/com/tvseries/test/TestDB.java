package com.tvseries.test;

import com.tvseries.dao.GenreDAO;
import com.tvseries.tables.Genre;

public class TestDB
{
    static public void main(String args[]) throws Exception
    {
        //int rows = GenreDAO.addGenre("horror");
        int rows = GenreDAO.updateGenre(3, "fantasy");
        Genre gn = GenreDAO.getGenre(3);
        System.out.println(gn.getName());
    }
}
