package com.tvseries.dao;

import com.tvseries.tables.Seasons_List;
import com.tvseries.utils.C3P0DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seasons_ListDAO
{
    public Seasons_ListDAO()
    {

    }

   static public Seasons_List getSeasons_List(int season_id, int user_id) throws Exception //TODO: return a list of the seasons
   {
        String query = "select * from t_seasons_list where season_id = " + season_id + "and user_id = " + user_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Seasons_List sl = new Seasons_List(season_id, user_id, rs.getDate("start_date"), rs.getDate("end_date"), rs.getInt("current_ep"), rs.getInt("status_id")); //create an actor with the information obtained

            st.close();
            rs.close();
            con.close();

            return sl;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }
}
