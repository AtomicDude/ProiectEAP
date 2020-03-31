package com.tvseries.tables;

public class Appears
{
    private int episode_id;
    private int character_id;

    public Appears(int episode_id, int character_id)
    {
        this.episode_id = episode_id;
        this.character_id = character_id;
    }

    public int getEpisode_id()
    {
        return episode_id;
    }

    public void setEpisode_id(int episode_id)
    {
        this.episode_id = episode_id;
    }

    public int getCharacter_id()
    {
        return character_id;
    }

    public void setCharacter_id(int character_id)
    {
        this.character_id = character_id;
    }
}
