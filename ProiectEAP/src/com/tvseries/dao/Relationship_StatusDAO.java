package com.tvseries.dao;

import com.tvseries.tables.Relationship_Status;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Relationship_StatusDAO {

    public Relationship_StatusDAO(){

    }

    static public Relationship_Status getRelationship_Status(int status_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_relationship_status where status_id = " + status_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Relationship_Status rl = new Relationship_Status(status_id, rs.getString("name")); //create a relationship status with the information obtained

            st.close();
            con.close();

            return rl;
        }

        st.close();
        con.close();

        return null;
    }
}
