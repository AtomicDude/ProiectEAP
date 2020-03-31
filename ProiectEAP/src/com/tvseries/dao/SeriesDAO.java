package com.tvseries.dao;

import com.tvseries.tables.Series;
import com.tvseries.utils.C3P0DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
}
