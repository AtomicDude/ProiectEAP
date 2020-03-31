package com.tvseries.dao;

import com.tvseries.tables.Character;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class CharacterDAO
{

    public CharacterDAO()
    {

    }

    static public Character getCharacter(int character_id) throws Exception
    {
        String query = "select * from t_character where character_id = " + character_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Character ch = new Character(character_id, rs.getString("first_name"), rs.getString("last_name"), rs.getInt("actor_id")); //create a character with the information obtained

            st.close();
            rs.close();
            con.close();

            return ch;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addCharacter(String first_name, String last_name, int actor_id) throws Exception
    {
        String query = "insert into t_character values(null, ?, ?, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, first_name);
        st.setString(2, last_name);
        st.setInt(3, actor_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateCharacter(int character_id, String first_name, String last_name, int actor_id) throws Exception
    {
        String query = "update t_character set first_name = ?, last_name = ?, actor_id = ? where character_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, first_name);
        st.setString(2, last_name);
        st.setInt(3, actor_id);
        st.setInt(4, character_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
