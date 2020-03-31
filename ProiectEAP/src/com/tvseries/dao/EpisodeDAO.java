package com.tvseries.dao;

import com.tvseries.tables.Episode;

import java.sql.*;

public class EpisodeDAO
{
    public EpisodeDAO()
    {

    }

    static public Episode getEpisode(int episode_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_episode where episode_id = " + episode_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Episode ep = new Episode(episode_id, rs.getString("title"), rs.getInt("ep_no"), rs.getInt("duration"), rs.getInt("season_id")); //create an episode with the information obtained

            st.close();
            con.close();

            return ep;
        }

        st.close();
        con.close();

        return null;
    }

    static public int addEpisode(String title, int ep_no, int duration, int season_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "insert into t_episode values(null, ?, ?, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, title);
        st.setInt(2, ep_no);
        st.setInt(3, duration);
        st.setInt(4, season_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateEpisode(int episode_id, String new_title, int new_ep_no, int new_duration, int new_season_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "update t_episode set title = ?, ep_no = ?, duration = ? season_id = ? where episode_id = ?";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_title);
        st.setInt(2, new_ep_no);
        st.setInt(3, new_duration);
        st.setInt(4, new_season_id);
        st.setInt(5, episode_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
