package com.tvseries.dao;

import com.tvseries.tables.Series;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class SeriesDAO
{
    public SeriesDAO()
    {

    }

    static public Series getSeries(int series_id) throws Exception
    {
        String query = "select * from t_series where series_id = " + series_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Series ser = new Series(series_id, rs.getString("title"), rs.getInt("rating_id")); //create a series with the information obtained

            st.close();
            rs.close();
            con.close();

            return ser;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addSeries(String title, int rating_id) throws Exception
    {
        String query = "insert into t_series(null,?,?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, title);
        st.setInt(2, rating_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int updateSeries(int series_id, String new_title, int new_rating_id) throws Exception
    {
        String query = "update t_series set title = ?, rating_id = ? where series_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_title);
        st.setInt(2, new_rating_id);
        st.setInt(3, series_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }
}
