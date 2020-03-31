package com.tvseries.tables;

public class Series
{
    private int series_id;
    String title;
    private int rating_id;

    public Series(int series_id, String title, int rating_id)
    {
        this.series_id = series_id;
        this.title = title;
        this.rating_id = rating_id;
    }

    public int getSeries_id()
    {
        return series_id;
    }

    public void setSeries_id(int series_id)
    {
        this.series_id = series_id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getRating_id()
    {
        return rating_id;
    }

    public void setRating_id(int rating_id)
    {
        this.rating_id = rating_id;
    }
}

