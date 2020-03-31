package com.tvseries.dao;

import com.tvseries.tables.Relationship;

import java.sql.*;

public class RelationshipDAO {

    public RelationshipDAO(){

    }

    static public boolean getRelationship(int user1_id, int user2_id, int status_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_relationship where user1_id = ? and user2_id = ? and status_id = ?";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a
        st.setInt(1, user1_id);
        st.setInt(2, user2_id);
        st.setInt(3, status_id);
        ResultSet rs = st.executeQuery(); //execute the query using the statement and store the result

        st.close();
        con.close();
        return rs.next();
    }
}
