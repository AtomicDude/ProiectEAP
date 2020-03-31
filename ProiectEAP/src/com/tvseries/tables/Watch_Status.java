package com.tvseries.tables;

public class Watch_Status {
    private int status_id;
    private String name;

    public Watch_Status() {

    }

    public Watch_Status(int status_id, String name) {
        this.status_id = status_id;
        this.name = name;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
