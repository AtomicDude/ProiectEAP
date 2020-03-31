package com.tvseries.dao;

import com.tvseries.tables.Character;

import java.sql.*;

public class CharacterDAO
{

    public CharacterDAO()
    {

    }

    static public Character getCharacter(int character_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_character where character_id = " + character_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Character ch = new Character(character_id, rs.getString("first_name"), rs.getString("last_name"), rs.getInt("actor_id")); //create a character with the information obtained

            st.close();
            con.close();

            return ch;
        }

        st.close();
        con.close();

        return null;
    }

    static public int addCharacter(String first_name, String last_name, int actor_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "insert into t_character values(null, ?, ?, ?)";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, first_name);
        st.setString(2, last_name);
        st.setInt(3, actor_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }

    static public int updateCharacter(int character_id, String first_name, String last_name, int actor_id) throws Exception
    {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "update t_character set first_name = ?, last_name = ?, actor_id = ? where character_id = ?";

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, first_name);
        st.setString(2, last_name);
        st.setInt(3, actor_id);
        st.setInt(4, character_id);

        int rows = st.executeUpdate(); //execute the query using the statement and store the result

        st.close(); //close the statement
        con.close(); //close the connection

        return rows; //return the number of rows affected
    }
}
