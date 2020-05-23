package com.tvseries.test;

import com.tvseries.containers.SearchListContainer;
import com.tvseries.containers.SeasonInfoContainer;
import com.tvseries.dao.*;
import com.tvseries.utils.C3P0DataSource;
import com.tvseries.utils.PasswordChecker;
import com.tvseries.utils.Triplet;
import javafx.util.Pair;
import org.apache.commons.validator.GenericValidator;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test
{
    public static void main(String[] args) throws Exception
    {
        PreparedStatement st;
        String query;
        String id_pool = "(null" + ",?".repeat(5) + ");";

        query = "delete from t_episodes_list where episode_id in " + id_pool;

        System.out.println(query);
    }
}
