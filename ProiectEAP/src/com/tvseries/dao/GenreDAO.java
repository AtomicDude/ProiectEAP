package com.tvseries.dao;

import com.tvseries.tables.Genre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenreDAO {

    public GenreDAO(){

    }

    static public Genre getGenre(int genre_id) throws Exception {
        String path = "jdbc:mysql://localhost:3306/tvseries_db";
        String dbuser = "admin1";
        String dbpassword = "admin1#password";
        String query = "select * from t_genre where genre_id = " + genre_id;

        Class.forName("com.mysql.cj.jdbc.Driver"); //load jdbc driver
        Connection con = DriverManager.getConnection(path, dbuser, dbpassword); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next()) {
            Genre gn = new Genre(genre_id, rs.getString("name")); //create an actor with the information obtained

            st.close();
            con.close();

            return gn;
        }

        st.close();
        con.close();

        return null;
    }
}
