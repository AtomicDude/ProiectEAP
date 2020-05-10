package com.tvseries.dao;

import com.tvseries.tables.Season;
import com.tvseries.tables.SeasonListItem;
import com.tvseries.tables.Seasons_List;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Seasons_ListDAO
{
    public Seasons_ListDAO()
    {

    }

   static public ArrayList<SeasonListItem> getSeasonList(String username, String status) throws Exception
   {
        String query = "select username, season_id, title, season_no, current_ep, score, w.name " +
                       "from t_user join t_seasons_list using(user_id) " +
                       "join t_season using(season_id) " +
                       "join t_watch_status w using(status_id) " +
                       "where username = " + "\"" + username + "\" " + "and w.name = " + "\"" + status + "\"";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        ArrayList<SeasonListItem> seasons = new ArrayList<SeasonListItem>();

        while(rs.next())
        {
            SeasonListItem s = new SeasonListItem(rs.getInt("season_id"), rs.getString("title"), rs.getInt("season_no"), rs.getInt("current_ep"), rs.getInt("score"), rs.getString("w.name"));

            seasons.add(s);
        }

        st.close();
        rs.close();
        con.close();

        return seasons;
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
