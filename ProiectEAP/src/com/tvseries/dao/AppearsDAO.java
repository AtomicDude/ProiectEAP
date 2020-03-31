package com.tvseries.dao;

import com.tvseries.tables.Appears;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class AppearsDAO
{
    public AppearsDAO()
    {

    }

    static public Appears getAppears(int episode_id) throws Exception
    {
        String query = "select * from t_appears where episode_id = " + episode_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if (rs.next())
        {
            Appears app = new Appears(episode_id, rs.getInt("character_id")); //create an actor with the information obtained

            st.close();
            rs.close();
            con.close();

            return app;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addAppears(int episode_id, int character_id) throws Exception
    {
        String query = "insert into t_appears values(?, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, episode_id);
        st.setInt(2, character_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateAppears(int episode_id, int character_id, int new_episode_id, int new_character_id) throws Exception
    {
        String query = "update t_appears set episode_id = ?, character_id = ? where episode_id = ? and character_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, new_episode_id);
        st.setInt(2, new_character_id);
        st.setInt(3, episode_id);
        st.setInt(4, character_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}