package com.tvseries.dao;

import com.tvseries.containers.SeriesInfoContainer;
import com.tvseries.utils.C3P0DataSource;
import com.tvseries.utils.Triplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeriesInfoDAO
{
    private static String white_list = "series_id, series_title, rating, series_poster";

    public static SeriesInfoContainer getSeriesInfo(Integer series_id) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;
        SeriesInfoContainer series_info = new SeriesInfoContainer();

        //build query
        query = "select series_id, series_title, rating, series_poster " +
                "from t_series " +
                "where series_id <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, series_id);
        //execute
        rs = st.executeQuery();

        //process results
        if(rs.next())
        {
            series_info.setSeries_id(rs.getObject("series_id", Integer.class));
            series_info.setSeries_title(rs.getObject("series_title", String.class));
            series_info.setRating(rs.getObject("rating", String.class));
            series_info.setSeries_poster(rs.getObject("series_poster", String.class));
        }

        //clean
        st.close();
        rs.close();

        //build query
        query = "select genre " +
                "from t_genre_list " +
                "where series_id <=> ?;";


        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, series_id);
        //execute
        rs = st.executeQuery();

        //process results
        List<String> genres = new ArrayList<String>();

        while(rs.next())
        {
            genres.add(rs.getString("genre"));
        }
        series_info.setGenres(genres);

        //clean
        st.close();
        rs.close();

        //build query
        query = "select season_id, season_title, season_poster " +
                "from t_season " +
                "where series_id <=> ?;";


        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, series_id);
        //execute
        rs = st.executeQuery();

        //process results
        List<Triplet<Integer, String, String>> seasons = new ArrayList<>();

        while(rs.next())
        {
            Triplet<Integer, String, String> season = new Triplet<>(rs.getObject("season_id", Integer.class), rs.getObject("season_title", String.class), rs.getObject("season_poster", String.class));
            seasons.add(season);
        }
        series_info.setSeasons(seasons);

        //clean
        st.close();
        rs.close();
        con.close();

        return series_info;
    }

    public static boolean exists(Integer series_id) throws Exception
    {
        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;
        ResultSet rs;

        //build query
        query = "select series_id from t_series where series_id <=> ?;";

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, series_id);
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

    public static boolean setSeriesAttribute(Integer series_id, String attribute_name, Object value) throws Exception
    {
        if(!white_list.contains(attribute_name))
        {
            return false;
        }

        Connection con = C3P0DataSource.getInstance().getConnection(); //establish connection
        String query;
        PreparedStatement st;

        //build query
        query = "update t_series set %s = ? where series_id <=> ?;";
        query = String.format(query, attribute_name);

        //prepare
        st = con.prepareStatement(query);
        //set parameters
        st.setObject(1, value);
        st.setObject(2, series_id);
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
