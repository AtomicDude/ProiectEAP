package com.tvseries.tables;

public class Rating {
    private int rating_id;
    private String name;

    public Rating() {

    }

    public Rating(int rating_id, String name) {
        this.rating_id = rating_id;
        this.name = name;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
