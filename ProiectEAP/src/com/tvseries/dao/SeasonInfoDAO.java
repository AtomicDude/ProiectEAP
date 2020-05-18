package com.tvseries.dao;

import com.tvseries.containers.SeasonInfoContainer;
import com.tvseries.utils.C3P0DataSource;
import com.tvseries.utils.Triplet;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeasonInfoDAO
{
    private static String white_list = "season_id, season_title, release_date, end_date, season_no, prequel_id, sequel_id, " +
            "series_id, season_poster";

    public static SeasonInfoContainer getSeasonInfo(Integer season_id, String username) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;
        SeasonInfoContainer season_info = new SeasonInfoContainer();

        //build query
        query = "select sea.season_id, sea.season_title, sea.release_date, sea.end_date, sea.season_no, sea.season_poster, " +
                "seali.watch_status, seali.current_ep, " +
                "pre.season_id as 'prequel_id', pre.season_title as 'prequel_title', pre.season_poster 'prequel_poster', " +
                "seq.season_id as 'sequel_id', seq.season_title as 'sequel_title', seq.season_poster as 'sequel_poster', " +
                "ser.series_id, ser.series_title, ser.series_poster " +
                "from t_season sea " +
                "right join t_seasons_list seali on(sea.season_id = seali.season_id) " +
                "right join t_season pre on(sea.season_id = pre.sequel_id) " +
                "right join t_season seq on(sea.season_id = seq.prequel_id) " +
                "join t_series ser on(sea.series_id = ser.series_id) " +
                "where sea.season_id <=> ? and username <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, season_id);
        st.setObject(2, username);
        //execute
        rs = st.executeQuery();

        //process results
        if(rs.next())
        {
            Triplet<Integer, String, String> prequel = new Triplet<>(rs.getObject("prequel_id", Integer.class), rs.getObject("prequel_title", String.class), rs.getObject("prequel_poster", String.class));
            Triplet<Integer, String, String> sequel = new Triplet<>(rs.getObject("sequel_id", Integer.class), rs.getObject("sequel_title", String.class), rs.getObject("sequel_poster", String.class));
            Triplet<Integer, String, String> series = new Triplet<>(rs.getObject("series_id", Integer.class), rs.getObject("series_title", String.class), rs.getObject("series_poster", String.class));

            season_info.setSeason_id(rs.getObject("season_id", Integer.class));
            season_info.setSeason_title(rs.getObject("season_title", String.class));
            season_info.setSeason_poster(rs.getObject("season_poster", String.class));
            season_info.setSeason_no(rs.getObject("season_no", Integer.class));
            season_info.setCurrent_ep(rs.getObject("current_ep", Integer.class));
            season_info.setWatch_status(rs.getObject("watch_status", String.class));
            season_info.setRelease_date(rs.getObject("release_date", LocalDate.class));
            season_info.setEnd_date(rs.getObject("end_date", LocalDate.class));
            season_info.setPrequel(prequel);
            season_info.setSequel(sequel);
            season_info.setSeries(series);
        }

        //clean
        st.close();
        rs.close();

        //build query
        query = "select episode_id, episode_title " +
                "from t_episode " +
                "where season_id <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, season_id);
        //execute
        rs = st.executeQuery();

        //process results
        List<Pair<Integer, String>> episodes = new ArrayList<>();

        while(rs.next())
        {
            episodes.add(new Pair<Integer, String>(rs.getObject("episode_id", Integer.class), rs.getObject("episode_title", String.class)));
        }
        season_info.setEpisodes(episodes);

        //clean
        st.close();
        rs.close();
        con.close();

        return season_info;
    }

    public static boolean exists(Integer season_id) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;

        //build query
        query = "select season_id from t_season where season_id <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, season_id);
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

    public static boolean setSeasonAttribute(Integer season_id, String attribute_name, Object value) throws Exception
    {
        if(!white_list.contains(attribute_name))
        {
            return false;
        }

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;

        //build query
        query = "update t_season set %s = ? where season_id <=> ?;";
        query = String.format(query, attribute_name);
        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, value);
        st.setObject(2, season_id);
        //execute
        int rows = st.executeUpdate();

        //process results
        boolean updated = rows > 0;

        //clean
        st.close();
        con.close();

        return updated;
    }
}
