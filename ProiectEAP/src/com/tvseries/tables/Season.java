package com.tvseries.tables;

import java.sql.Date;

public class Season {
    private int season_id;
    private String title;
    private Date release_date;
    private Date end_date;
    private float avg_score;
    private int popularity;
    private int season_no;
    private int prequel_id;
    private int sequel_id;
    private int series_id;

    public Season() {

    }

    public Season(int season_id, String title, Date release_date, Date end_date, float avg_score, int popularity, int season_no, int prequel_id, int sequel_id, int series_id) {
        this.season_id = season_id;
        this.title = title;
        this.release_date = release_date;
        this.end_date = end_date;
        this.avg_score = avg_score;
        this.popularity = popularity;
        this.season_no = season_no;
        this.prequel_id = prequel_id;
        this.sequel_id = sequel_id;
        this.series_id = series_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public float getAvg_score() {
        return avg_score;
    }

    public void setAvg_score(float avg_score) {
        this.avg_score = avg_score;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getSeason_no() {
        return season_no;
    }

    public void setSeason_no(int season_no) {
        this.season_no = season_no;
    }

    public int getPrequel_id() {
        return prequel_id;
    }

    public void setPrequel_id(int prequel_id) {
        this.prequel_id = prequel_id;
    }

    public int getSequel_id() {
        return sequel_id;
    }

    public void setSequel_id(int sequel_id) {
        this.sequel_id = sequel_id;
    }

    public int getSeries_id() {
        return series_id;
    }

    public void setSeries_id(int series_id) {
        this.series_id = series_id;
    }
}
