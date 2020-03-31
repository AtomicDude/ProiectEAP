package com.tvseries.tables;

public class Genre_List
{
    private int genre_id;
    private int series_id;

    public Genre_List(int genre_id, int series_id)
    {
        this.genre_id = genre_id;
        this.series_id = series_id;
    }

    public int getGenre_id()
    {
        return genre_id;
    }

    public void setGenre_id(int genre_id)
    {
        this.genre_id = genre_id;
    }

    public int getSeries_id()
    {
        return series_id;
    }

    public void setSeries_id(int series_id)
    {
        this.series_id = series_id;
    }
}
