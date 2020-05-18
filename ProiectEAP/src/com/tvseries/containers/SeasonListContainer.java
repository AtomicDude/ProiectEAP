package com.tvseries.containers;

import java.time.LocalDate;

public class SeasonListContainer
{
    private Integer season_id;
    private String season_title;
    private LocalDate my_start;
    private LocalDate my_end;
    private String watch_status;
    private Integer current_ep;
    private String season_poster;

    public SeasonListContainer()
    {

    }

    public SeasonListContainer(Integer season_id, String season_title, LocalDate my_start, LocalDate my_end, String watch_status, Integer current_ep, String season_poster)
    {
        this.season_id = season_id;
        this.season_title = season_title;
        this.my_start = my_start;
        this.my_end = my_end;
        this.watch_status = watch_status;
        this.current_ep = current_ep;
        this.season_poster = season_poster;
    }

    public Integer getSeason_id()
    {
        return season_id;
    }

    public void setSeason_id(Integer season_id)
    {
        this.season_id = season_id;
    }

    public String getSeason_title()
    {
        return season_title;
    }

    public void setSeason_title(String season_title)
    {
        this.season_title = season_title;
    }

    public LocalDate getMy_start()
    {
        return my_start;
    }

    public void setMy_start(LocalDate my_start)
    {
        this.my_start = my_start;
    }

    public LocalDate getMy_end()
    {
        return my_end;
    }

    public void setMy_end(LocalDate my_end)
    {
        this.my_end = my_end;
    }

    public String getWatch_status()
    {
        return watch_status;
    }

    public void setWatch_status(String watch_status)
    {
        this.watch_status = watch_status;
    }

    public Integer getCurrent_ep()
    {
        return current_ep;
    }

    public void setCurrent_ep(Integer current_ep)
    {
        this.current_ep = current_ep;
    }

    public String getSeason_poster()
    {
        return season_poster;
    }

    public void setSeason_poster(String season_poster)
    {
        this.season_poster = season_poster;
    }
}
