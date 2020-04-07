package com.tvseries.dao;

import com.tvseries.tables.Season;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class SeasonDAO
{
    public SeasonDAO()
    {

    }

    static public Season getSeason(int season_id) throws Exception
    {
        String query = "select * from t_season where season_id = " + season_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Season sz = new Season(season_id, rs.getString("title"), rs.getDate("release_date"), rs.getDate("end_date"), rs.getFloat("avg_score"), rs.getInt("popularity"), rs.getInt("season_no"), rs.getInt("prequel_id"), rs.getInt("sequel_id"), rs.getInt("series_id")); //create a season with the information obtained

            st.close();
            rs.close();
            con.close();

            return sz;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addSeason(String title, Date release_date, Date end_date, float avg_score, int popularity, int season_no, int prequel_id, int sequel_id, int series_id) throws Exception
    {
        String query = "insert into t_season values(null,?,?,?,?,?,?,?,?,?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, title);
        st.setDate(2,release_date);
        st.setDate(3,end_date);
        st.setFloat(4, avg_score);
        st.setInt(5, popularity);
        st.setInt(6, season_no);
        st.setInt(7, prequel_id);
        st.setInt(8, sequel_id);
        st.setInt(9, series_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int updateSeason(int season_id, String new_title, Date new_release_date, Date new_end_date, float new_avg_score, int new_popularity, int new_season_no, int new_prequel_id, int new_sequel_id, int new_series_id) throws Exception
    {
        String query = "update t_season set title = ?, release_date = ?, end_date = ?, avg_score = ?, popularity = ?, season_no = ?, prequel_id = ?, sequel_id = ?, series_id = ? where season_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_title);
        st.setDate(2, new_release_date);
        st.setDate(3, new_end_date);
        st.setFloat(4, new_avg_score);
        st.setInt(5, new_popularity);
        st.setInt(6, new_season_no);
        st.setInt(7, new_prequel_id);
        st.setInt(8, new_sequel_id);
        st.setInt(9, new_series_id);
        st.setInt(10, season_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

}
