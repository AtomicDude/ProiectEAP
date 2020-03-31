package com.tvseries.dao;

import com.tvseries.tables.Genre;
import com.tvseries.tables.Genre_List;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;
import java.util.ArrayList;

public class Genre_ListDAO {

    public Genre_ListDAO(){

    }

    static public ArrayList<Genre> getGenre_List(int series_id) throws Exception
    {
        String query = "select * from t_genre_list where series_id = " + series_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        ArrayList<Genre> gnlist = new ArrayList<Genre>();

        while(rs.next())
        {
            gnlist.add(GenreDAO.getGenre(rs.getInt("genre_id")));
        }

        st.close();
        rs.close();
        con.close();

        return gnlist;
    }

    static public int addGenre_List(int genre_id, int series_id) throws Exception
    {
        String query = "insert into t_genre_list values(?, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, genre_id);
        st.setInt(2, series_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateGenre_List(int genre_id, int series_id, int new_genre_id, int new_series_id) throws Exception //TODO: de terminat
    {
        String query = "update t_genre_list set genre_id = ?, series_id = ? where genre_id = ? and series_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, new_genre_id);
        st.setInt(2, new_series_id);
        st.setInt(3, genre_id);
        st.setInt(4, series_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
