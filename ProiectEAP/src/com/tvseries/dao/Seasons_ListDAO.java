package com.tvseries.dao;

import com.tvseries.tables.Seasons_List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Seasons_ListDAO {

    public Seasons_ListDAO(){

    }

   static public Seasons_List getSeasons_List(int season_id, int user_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_seasons_list where season_id = " + season_id + "and user_id = " + user_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Seasons_List sl = new Seasons_List(season_id, user_id, rs.getDate("start_date"), rs.getDate("end_date"), rs.getInt("current_ep"), rs.getInt("status_id")); //create an actor with the information obtained

            st.close();
            con.close();

            return sl;
        }

        st.close();
        con.close();

        return null;
    }
}
