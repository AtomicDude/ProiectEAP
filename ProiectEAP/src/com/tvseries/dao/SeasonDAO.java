package com.tvseries.dao;

import com.tvseries.tables.Season;
import com.tvseries.utils.C3P0DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
