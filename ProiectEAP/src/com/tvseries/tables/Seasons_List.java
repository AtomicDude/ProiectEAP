package com.tvseries.tables;

import java.sql.Date;

public class Seasons_List {
    private int season_id;
    private int user_id;
    private Date start_date;
    private Date end_date;
    private int current_ep;
    private int status_id;

    public Seasons_List() {

    }

    public Seasons_List(int season_id, int user_id, Date start_date, Date end_date, int current_ep, int status_id) {
        this.season_id = season_id;
        this.user_id = user_id;
        this.start_date = start_date;
        this.end_date = end_date;
        this.current_ep = current_ep;
        this.status_id = status_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getCurrent_ep() {
        return current_ep;
    }

    public void setCurrent_ep(int current_ep) {
        this.current_ep = current_ep;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
}
