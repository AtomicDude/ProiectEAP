package com.tvseries.dao;

import com.tvseries.tables.Character;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CharacterDAO {

    public CharacterDAO(){

    }

    static public Character getCharacter(int character_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_character where character_id = " + character_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Character ch = new Character(character_id, rs.getString("first_name"), rs.getString("last_name"), rs.getInt("actor_id")); //create a character with the information obtained

            st.close();
            con.close();

            return ch;
        }

        st.close();
        con.close();

        return null;
    }
}
