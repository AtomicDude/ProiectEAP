package com.tvseries.dao;

import com.tvseries.tables.User;
import com.tvseries.utils.C3P0DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO
{
    public UserDAO()
    {

    }

    static public User getUser(int user_id) throws Exception
    {
        String query = "select * from t_user where user_id = " + user_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            User usr = new User(user_id, rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"),rs.getString("last_name"),rs.getDate("birth_date")); //create an actor with the information obtained

            st.close();
            rs.close();
            con.close();

            return usr;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }
}
