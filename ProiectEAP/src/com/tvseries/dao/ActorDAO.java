package com.tvseries.dao;

import com.tvseries.tables.Actor;

import java.sql.*;

public class ActorDAO
{
    public ActorDAO()
    {

    }

    static public Actor getActor(int actor_id) throws Exception
    {
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

    static public int addActor(String first_name, String last_name, Date birth_date) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "insert into t_actor values(null, ?, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
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
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "update t_actor set first_name = ?, last_name = ?, birth_date = ? where actor_id = ?";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
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
}
