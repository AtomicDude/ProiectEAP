package com.tvseries.dao;

import com.tvseries.tables.Relationship_Status;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class Relationship_StatusDAO
{
    public Relationship_StatusDAO()
    {

    }

    static public Relationship_Status getRelationship_Status(int status_id) throws Exception
    {
        String query = "select * from t_relationship_status where status_id = " + status_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Relationship_Status rl = new Relationship_Status(status_id, rs.getString("name")); //create a relationship status with the information obtained

            st.close();
            rs.close();
            con.close();

            return rl;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addRelationship_Status(String name) throws Exception
    {
        String query = "insert into t_genre values(null, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, name);
        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateGenre(int genre_id, String new_name) throws Exception
    {
        String query = "update t_genre set name = ? where genre_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_name);
        st.setInt(2, genre_id);
        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
