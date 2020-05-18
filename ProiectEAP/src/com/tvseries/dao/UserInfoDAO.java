package com.tvseries.dao;

import com.tvseries.containers.SeasonListContainer;
import com.tvseries.containers.UserInfoContainer;
import com.tvseries.utils.C3P0DataSource;
import com.tvseries.utils.Triplet;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO
{
    private static String white_list = "username,display_name,email,password,first_name,last_name,birth_date,picture_path,settings_id";

    public static UserInfoContainer getUserInfo(String username) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;
        UserInfoContainer user_info = new UserInfoContainer();

        //build query
        query = "select username, display_name, first_name, last_name, birth_date, picture_path " +
                "from t_user " +
                "where username <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, username);
        //execute
        rs = st.executeQuery();

        //process results
        if(rs.next())
        {
            user_info.setUsername(rs.getObject("username", String.class));
            user_info.setDisplay_name(rs.getObject("display_name", String.class));
            user_info.setFirst_name(rs.getObject("first_name", String.class));
            user_info.setLast_name(rs.getObject("last_name", String.class));
            user_info.setBirth_date(rs.getObject("birth_date", LocalDate.class));
            user_info.setPicture_path(rs.getObject("picture_path", String.class));
        }

        //clean
        st.close();
        rs.close();

        //build query
        query = "select username2, display_name, picture_path " +
                "from t_relationship join t_user on(username1 = username) " +
                "where username <=> ? and relation_status = \"friends\";";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, username);
        //execute
        rs = st.executeQuery();

        //process results
        List<Triplet<String, String, String>> friends = new ArrayList<>();

        while(rs.next())
        {
            friends.add(new Triplet<>(rs.getObject("username2", String.class), rs.getObject("display_name", String.class), rs.getObject("picture_path", String.class)));
        }
        user_info.setFriends(friends);

        //clean
        st.close();
        rs.close();
        con.close();

        return user_info;
    }

    public static List<SeasonListContainer> getUserList(String username, String watch_status) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<SeasonListContainer> season_list = new ArrayList<>();

        //build query
        query = "select season_id, season_title, my_start, my_end, watch_status, current_ep, season_poster " +
                "from t_seasons_list join t_season using(season_id) " +
                "where username <=> ? and watch_status <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, username);
        st.setObject(2, watch_status);
        //execute
        rs = st.executeQuery();

        while(rs.next())
        {
            SeasonListContainer season = new SeasonListContainer();
            season.setSeason_id(rs.getObject("season_id", Integer.class));
            season.setSeason_title(rs.getObject("season_title", String.class));
            season.setSeason_poster(rs.getObject("season_poster", String.class));
            season.setWatch_status(rs.getObject("watch_status", String.class));
            season.setCurrent_ep(rs.getObject("current_ep", Integer.class));
            season.setMy_start(rs.getObject("my_start", LocalDate.class));
            season.setMy_end(rs.getObject("my_end", LocalDate.class));

            season_list.add(season);
        }

        //clean
        st.close();
        rs.close();
        con.close();

        return season_list;
    }

    public static Object getUserAttribute(String username, String attribute_name) throws Exception
    {
        if(!white_list.contains(attribute_name))
        {
            return null;
        }

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;
        Object attribute = null;

        //build query
        query = "select %s " +
                "from t_user " +
                "where username <=> ?;";
        query = String.format(query, attribute_name);

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, username);
        //execute
        rs = st.executeQuery();

        if(rs.next())
        {
            attribute = rs.getObject(attribute_name);
        }

        //clean
        st.close();
        rs.close();
        con.close();

        return attribute;
    }

    public static boolean exists(String username) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;

        //build query
        query = "select username from t_user where username <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, username);
        //execute
        rs = st.executeQuery();

        //process results
        boolean exists = rs.next();

        //clean
        st.close();
        rs.close();
        con.close();

        return exists;
    }

    public static boolean setUserAttribute(String username, String attribute_name, Object value) throws Exception
    {
        if(!white_list.contains(attribute_name))
        {
            return false;
        }

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;

        //build query
        query = "update t_user set %s = ? where username <=> ?;";
        query = String.format(query, attribute_name);

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, value);
        st.setObject(2, username);
        //execute
        int rows = st.executeUpdate();

        //process results
        boolean updated = rows > 0;

        //clean
        st.close();
        con.close();

        return updated;
    }

    public static boolean addUser(List<Pair<String, Object>> attributes) throws Exception
    {
        int attributes_no = attributes.size();

        if(attributes_no < 1)
        {
            return false;
        }

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;

        //build query
        String columns = "%s";
        String column_name = attributes.get(0).getKey();
        if(!white_list.contains(column_name))
        {
            return false;
        }

        columns = String.format(columns, column_name);
        String values = "?";
        for(int i = 1; i < attributes_no; i++)
        {
            columns += ", %s";
            column_name = attributes.get(i).getKey();
            columns = String.format(columns, column_name);
            if(!white_list.contains(column_name))
            {
                System.out.println(column_name);
                return false;
            }

            values += ", ?";
        }
        query = "insert into t_user(" + columns + ") values(" + values + ");";

        System.out.println(query);
        //prepare
        st = con.prepareStatement(query);

        //set parameters
        for(int i = 0; i < attributes_no; i++)
        {
            st.setObject(i + 1, attributes.get(i).getValue()); //set the attribute value
        }

        //execute
        boolean inserted = st.execute();

        //clean
        st.close();
        con.close();

        return inserted;
    }
}
