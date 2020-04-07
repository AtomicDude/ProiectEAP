package com.tvseries.dao;

import com.tvseries.tables.Watch_Status;
import com.tvseries.utils.C3P0DataSource;

import java.sql.*;

public class Watch_StatusDAO
{
    public Watch_StatusDAO()
    {

    }

    static public Watch_Status getWatch_Status(int status_id) throws Exception
    {
        String query = "select * from t_watch_status where status_id = " + status_id;

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        Statement st = con.createStatement(); //create a statement
        ResultSet rs = st.executeQuery(query); //execute the query using the statement and store the result

        if(rs.next())
        {
            Watch_Status ws = new Watch_Status(status_id, rs.getString("name")); //create an actor with the information obtained

            st.close();
            rs.close();
            con.close();

            return ws;
        }

        st.close();
        rs.close();
        con.close();

        return null;
    }

    static public int addWatch_Status(String name) throws Exception
    {
        String query = "insert into t_watch_status(null,?)";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, name);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }

    static public int updateWatch_Status(int status_id, String new_name) throws Exception
    {
        String query = "update t_watch_status set name = ? where status_id = ?";

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        PreparedStatement st = con.prepareStatement(query); //create a statement
        st.setString(1, new_name);
        st.setInt(2, status_id);

        int rows = st.executeUpdate();

        st.close();
        con.close();

        return rows;
    }
}
