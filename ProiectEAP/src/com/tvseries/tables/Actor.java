package com.tvseries.tables;

import java.time.LocalDate;

public class Actor
{
    private int actor_id;
    private String first_name;
    private String last_name;
    private LocalDate birth_date;

    public Actor(int actor_id, String first_name, String last_name, LocalDate birth_date)
    {
        this.actor_id = actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
    }

    public int getActor_id()
    {
        return actor_id;
    }

    public void setActor_id(int actor_id)
    {
        this.actor_id = actor_id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public LocalDate getBirth_date()
    {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date)
    {
        this.birth_date = birth_date;
    }
}
