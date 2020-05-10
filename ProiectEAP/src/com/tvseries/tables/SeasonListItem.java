package com.tvseries.tables;

import org.w3c.dom.html.HTMLIsIndexElement;

import java.time.LocalDate;

public class SeasonListItem
{
    private int season_id;
    private String title;
    private int current_ep;
    private int score;
    private String status;
    private LocalDate release_date;
    private LocalDate end_date;
    private float avg_score;
    private int popularity;
    private int season_no;
    private int prequel_id;
    private int sequel_id;
    private int series_id;

    public SeasonListItem(int season_id, String title, int season_no, int current_ep, int score, String status)
    {
        this.season_id = season_id;
        this.title = title;
        this.season_no = season_no;
        this.current_ep = current_ep;
        this.score = score;
        this.status = status;
    }

    public int getSeason_id()
    {
        return season_id;
    }

    public void setSeason_id(int season_id)
    {
        this.season_id = season_id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public int getCurrent_ep()
    {
        return current_ep;
    }

    public void setCurrent_ep(int current_ep)
    {
        this.current_ep = current_ep;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public LocalDate getRelease_date()
    {
        return release_date;
    }

    public void setRelease_date(LocalDate release_date)
    {
        this.release_date = release_date;
    }

    public LocalDate getEnd_date()
    {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date)
    {
        this.end_date = end_date;
    }

    public float getAvg_score()
    {
        return avg_score;
    }

    public void setAvg_score(float avg_score)
    {
        this.avg_score = avg_score;
    }

    public int getPopularity()
    {
        return popularity;
    }

    public void setPopularity(int popularity)
    {
        this.popularity = popularity;
    }

    public int getSeason_no()
    {
        return season_no;
    }

    public void setSeason_no(int season_no)
    {
        this.season_no = season_no;
    }

    public int getPrequel_id()
    {
        return prequel_id;
    }

    public void setPrequel_id(int prequel_id)
    {
        this.prequel_id = prequel_id;
    }

    public int getSequel_id()
    {
        return sequel_id;
    }

    public void setSequel_id(int sequel_id)
    {
        this.sequel_id = sequel_id;
    }

    public int getSeries_id()
    {
        return series_id;
    }

    public void setSeries_id(int series_id)
    {
        this.series_id = series_id;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
