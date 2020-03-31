package com.tvseries.tables;

public class Binge_Watching {
    private int user1_id;
    private int user2_id;
    private int season_id;

    public Binge_Watching() {

    }

    public Binge_Watching(int user1_id, int user2_id, int season_id) {
        this.user1_id = user1_id;
        this.user2_id = user2_id;
        this.season_id = season_id;
    }

    public int getUser1_id() {
        return user1_id;
    }

    public void setUser1_id(int user1_id) {
        this.user1_id = user1_id;
    }

    public int getUser2_id() {
        return user2_id;
    }

    public void setUser2_id(int user2_id) {
        this.user2_id = user2_id;
    }

    public int getSeason_id() {
        return season_id;
    }

    public void setSeason_id(int season_id) {
        this.season_id = season_id;
    }
}
