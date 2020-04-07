package com.tvseries.dao;

import com.tvseries.tables.Relationship;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class RelationshipDAO
{
    public RelationshipDAO()
    {

    }

    static public boolean getRelationship(int user1_id, int user2_id, int status_id) throws Exception
    {
        String query = "select * from t_relationship where user1_id = ? and user2_id = ? and status_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a
        st.setInt(1, user1_id);
        st.setInt(2, user2_id);
        st.setInt(3, status_id);
        ResultSet rs = st.executeQuery(); //execute the query using the statement and store the result

        st.close();
        con.close();
        return rs.next();
    }

    static public int addRelationship(int user1_id, int user2_id, int status_id) throws Exception
    {
        String query = "insert into t_relationship values(?,?,?)";
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, user1_id);
        st.setInt(2, user2_id);
        st.setInt(3, status_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int updateRelationship(int user1_id, int user2_id, int new_status_id) throws Exception
    {
        String query = "update t_relationship set status_id = ? where user1_id = ? and user2_id = ?";
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, new_status_id);
        st.setInt(2, user1_id);
        st.setInt(3, user2_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }
}
