package com.tvseries.dao;

import com.tvseries.tables.Seasons_List;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;
import java.time.LocalDate;

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
            Seasons_List sl = new Seasons_List(season_id, user_id, rs.getDate("start_date").toLocalDate(), rs.getDate("end_date").toLocalDate(), rs.getInt("current_ep"), rs.getInt("status_id")); //create an actor with the information obtained

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

    static public int addSeasons_List(int season_id, int user_id, LocalDate start_date, LocalDate end_date, int current_ep, int status_id ) throws Exception
    {
        String query = "insert into t_seasons_list(season_id, user_id, start_date, end_date, current_ep, status_id)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, season_id);
        st.setInt(2, user_id);
        st.setDate(3, Date.valueOf(start_date));
        st.setDate(4, Date.valueOf(end_date));
        st.setInt(5, current_ep);
        st.setInt(6, status_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int updateSeasons_List(int season_id, int user_id, LocalDate new_start_date, LocalDate new_end_date, int new_current_ep, int new_status_id ) throws Exception
    {
        String query = "update t_seasons_list set start_date = ?, end_date = ?, current_ep = ?, status_id = ? where season_id = ? and user_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setDate(1, Date.valueOf(new_start_date));
        st.setDate(2, Date.valueOf(new_end_date));
        st.setInt(3, new_current_ep);
        st.setInt(4, new_status_id);
        st.setInt(5, season_id);
        st.setInt(6, user_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int deleteSeasons_List(int season_id, int user_id) throws Exception
    {
        String query = "delete from t_seasons_list where season_id = ? and user_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, season_id);
        st.setInt(2, user_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }
}
