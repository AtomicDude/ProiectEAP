package com.tvseries.dao;

import com.tvseries.tables.Genre_List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Genre_ListDAO {

    public Genre_ListDAO(){

    }

    static public Genre_List getGenre_List(int ) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_actor where actor_id = " + actor_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Actor ac = new Actor(actor_id, rs.getString("first_name"), rs.getString("last_name"), rs.getDate("birth_date")); //create an actor with the information obtained

            st.close();
            con.close();

            return ac;
        }

        st.close();
        con.close();

        return null;
    }
}
