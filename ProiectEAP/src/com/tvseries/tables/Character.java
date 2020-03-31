package com.tvseries.tables;

public class Character
{

    private int character_id;
    private String first_name;
    private String last_name;
    private int actor_id;

    public Character(int character_id, String first_name, String last_name, int actor_id)
    {
        this.character_id = character_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.actor_id = actor_id;
    }

    public int getCharacter_id()
    {
        return character_id;
    }

    public void setCharacter_id(int character_id)
    {
        this.character_id = character_id;
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

    public int getActor_id()
    {
        return actor_id;
    }

    public void setActor_id(int actor_id)
    {
        this.actor_id = actor_id;
    }
}
