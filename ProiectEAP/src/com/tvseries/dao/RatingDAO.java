package com.tvseries.dao;

import com.tvseries.tables.Rating;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class RatingDAO
{

    public RatingDAO()
    {

    }

    static public Rating getRating(int rating_id) throws Exception
    {
        String query = "select * from t_rating where rating_id = " + rating_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Rating rt = new Rating(rating_id, rs.getString("name")); //create a rating with the information obtained

            st.close();
            rs.close();
            con.close();

            return rt;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addRating(String name) throws Exception
    {
        String query = "insert into t_rating values(null, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, name);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateRating(int rating_id, String new_name) throws Exception
    {
        String query = "update t_rating set name = ? where rating_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_name);
        st.setInt(2, rating_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
