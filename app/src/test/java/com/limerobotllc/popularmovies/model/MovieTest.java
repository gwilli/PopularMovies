package com.limerobotllc.popularmovies.model;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MovieTest
{
    @Test
    public void testDecodeMovie()
    {
        Gson gson = new Gson();
        InputStream stream  = MovieResults.class.getResourceAsStream("/movie.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Movie movie = gson.fromJson(reader, Movie.class);

        assertNotNull("movie is null", movie);
        assertFalse("adult", movie.adult);
        assertEquals("/bIlYH4l2AyYvEysmS2AOfjO7Dn8.jpg", movie.backdropPath);
        assertArrayEquals(new int[]{878, 28, 53, 12}, movie.genreIds);
        assertEquals(87101, movie.id);
        assertEquals("en", movie.originalLanguage);
        assertEquals("Terminator Genisys", movie.originalTitle);
        assertTrue(movie.overview.startsWith(
                "The year is 2029. John Connor, leader of the resistance continues the war against the machines."));
        assertEquals("2015-07-01", movie.releaseDateString);
        assertEquals("/5JU9ytZJyR3zmClGmVm9q4Geqbd.jpg", movie.posterPath);
        assertEquals(55.972492, movie.popularity, 0.000001);
        assertEquals("Terminator Genisys", movie.title);
        assertFalse("video", movie.video);
        assertEquals(6.3, movie.voteAverage, 0.01);
        assertEquals(698, movie.voteCount);
    }
}
