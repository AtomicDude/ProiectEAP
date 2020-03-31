package com.tvseries.dao;

import com.tvseries.tables.Appears;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AppearsDAO{

    public AppearsDAO(){

    }

    static public Appears getAppears(int episode_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_appears where episode_id = " + episode_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Appears app = new Appears(episode_id, rs.getInt("character_id")); //create an actor with the information obtained

            st.close();
            con.close();

            return app;
        }

        st.close();
        con.close();

        return null;
    }
}