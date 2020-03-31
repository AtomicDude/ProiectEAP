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
}
