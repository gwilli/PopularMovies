package com.limerobotllc.popularmovies.model;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

public class MovieResultsTest
{
    @Test
    public void testDecodeMovieResults() throws Exception
    {
        Gson gson = new Gson();
        InputStream stream  = MovieResults.class.getResourceAsStream("/popular_movies_result.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        MovieResults movieResults = gson.fromJson(reader, MovieResults.class);

        assertNotNull("movieresults is null", movieResults);
        assertEquals(1, movieResults.page);
        assertEquals(20, movieResults.results.length);
    }
}
