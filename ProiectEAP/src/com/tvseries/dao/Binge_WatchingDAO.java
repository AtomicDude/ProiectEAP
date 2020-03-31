package com.tvseries.dao;

import com.tvseries.tables.Binge_Watching;

import java.sql.*;

public class Binge_WatchingDAO
{
    public Binge_WatchingDAO()
    {

    }

    static public boolean getBinge_Watching(int user1_id, int user2_id, int season_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_binge_watching where user1_id = ? and user2_id = ? and season_id = ?";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a
        st.setInt(1, user1_id);
        st.setInt(2, user2_id);
        st.setInt(3, season_id);
        ResultSet rs = st.executeQuery(); //execute the query using the statement and store the result

        st.close();
        con.close();
        return rs.next();
    }

    static public int addBinge_Watching(int user1_id, int user2_id, int season_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "insert into t_binge_watching values(?, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, user1_id);
        st.setInt(2, user2_id);
        st.setInt(3, season_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateBinge_Watching(int user1_id, int user2_id, int season_id, int new_user1_id, int new_user2_id, int new_season_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "update t_binge_watching set user1_id = ?, user2_id = ?, season_id = ? where user1_id = ? and user2_id = ? and season_id = ?";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, new_user1_id);
        st.setInt(2, new_user2_id);
        st.setInt(3, new_season_id);
        st.setInt(4, user1_id);
        st.setInt(5, user2_id);
        st.setInt(6, season_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
