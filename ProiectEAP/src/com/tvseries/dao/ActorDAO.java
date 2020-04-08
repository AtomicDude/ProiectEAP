package com.tvseries.dao;

import com.tvseries.tables.Actor;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class ActorDAO
{
    public ActorDAO()
    {

    }

    static public Actor getActor(int actor_id) throws Exception
    {
        String query = "select * from t_actor where actor_id = " + actor_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Actor ac = new Actor(actor_id, rs.getString("first_name"), rs.getString("last_name"), rs.getDate("birth_date")); //create an actor with the information obtained

            st.close();
            rs.close();
            con.close();

            return ac;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addActor(String first_name, String last_name, Date birth_date) throws Exception
    {
        String query = "insert into t_actor values(null, ?, ?, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, first_name);
        st.setString(2, last_name);
        st.setDate(3, birth_date);
        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateActor(int actor_id, String new_first_name, String new_last_name, Date new_birth_date) throws Exception
    {
        String query = "update t_actor set first_name = ?, last_name = ?, birth_date = ? where actor_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_first_name);
        st.setString(2, new_last_name);
        st.setDate(3, new_birth_date);
        st.setInt(4, actor_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int removeActor(int actor_id) throws Exception
    {
        String query = "delete from t_actor where actor_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, actor_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }
}
