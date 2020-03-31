package com.tvseries.tables;

public class Episode
{
    private int episode_id;
    private String title;
    private int ep_no;
    private int duration;
    private int season_id;

    public Episode(int episode_id, String title, int ep_no, int duration, int season_id)
    {
        this.episode_id = episode_id;
        this.title = title;
        this.ep_no = ep_no;
        this.duration = duration;
        this.season_id = season_id;
    }

    public int getEpisode_id()
    {
        return episode_id;
    }

    public void setEpisode_id(int episode_id)
    {
        this.episode_id = episode_id;
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

    public int getEp_no()
    {
        return ep_no;
    }

    public void setEp_no(int ep_no)
    {
        this.ep_no = ep_no;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }
}
