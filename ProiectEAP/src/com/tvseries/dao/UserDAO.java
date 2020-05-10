package com.tvseries.dao;

import com.tvseries.tables.User;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;
import java.time.LocalDate;

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
            User usr = new User(user_id, rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("first_name"),rs.getString("last_name"),rs.getDate("birth_date").toLocalDate()); //create an actor with the information obtained

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

    static public String getPassword(String uname) throws Exception
    {
        String query = "select password from t_user where username = \"" + uname + "\"";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            String pass = rs.getString("password");
            st.close();
            rs.close();
            con.close();

            return pass;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addUser(String username, String display_name, String email, String password, String first_name, String last_name, LocalDate birth_date) throws Exception
    {
        String query = "insert into t_user values(null, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, username);
        st.setString(2, display_name);
        st.setString(3, email);
        st.setString(4, password);
        st.setString(5, first_name);
        st.setString(6, last_name);
        st.setDate(7, Date.valueOf(birth_date));

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int updateUser(int user_id, String new_username, String new_email, String new_password, String new_first_name, String new_last_name, LocalDate new_birth_date) throws Exception
    {
        String query = "update t_user set username = ?, email = ?, password = ?, first_name = ?, last_name = ?, birth_date = ? where user_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_username);
        st.setString(2, new_email);
        st.setString(3, new_password);
        st.setString(4, new_first_name);
        st.setString(5,new_last_name);
        st.setDate(6, Date.valueOf(new_birth_date));
        st.setInt(7, user_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int deleteUser(int user_id) throws Exception
    {
        String query = "delet from t_user where user_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setInt(1, user_id);

        int rows = st.executeUpdate();


        st.close();
        con.close();

        return rows;
    }
}
