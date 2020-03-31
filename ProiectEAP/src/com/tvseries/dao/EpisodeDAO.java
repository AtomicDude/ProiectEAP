package com.tvseries.dao;

import com.tvseries.tables.Episode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class EpisodeDAO {

    public EpisodeDAO(){

    }

    static public Episode getEpisode(int episode_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_episode where episode_id = " + episode_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Episode ep = new Episode(episode_id, rs.getString("title"), rs.getInt("ep_no"), rs.getInt("duration"), rs.getInt("season_id")); //create an episode with the information obtained

            st.close();
            con.close();

            return ep;
        }

        st.close();
        con.close();

        return null;
    }
}
