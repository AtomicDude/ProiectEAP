package com.tvseries.dao;

import com.tvseries.tables.Series;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SeriesDAO {

    public SeriesDAO(){

    }

    static public Series getSeries(int series_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_series where series_id = " + series_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Series ser = new Series(series_id, rs.getString("title"), rs.getInt("rating_id")); //create a series with the information obtained

            st.close();
            con.close();

            return ser;
        }

        st.close();
        con.close();

        return null;
    }
}
