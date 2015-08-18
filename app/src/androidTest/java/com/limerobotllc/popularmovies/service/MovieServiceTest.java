package com.limerobotllc.popularmovies.service;

import android.test.AndroidTestCase;

import com.limerobotllc.popularmovies.R;
import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.model.MovieResults;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

public class MovieServiceTest extends AndroidTestCase
{
    private static final String LOG_KEY = MovieServiceTest.class.getSimpleName();

    private String apiKey;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        apiKey = mContext.getString(R.string.tmdb_api_key);
    }

    public void testDiscoverMovies()
    {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.themoviedb.org")
                .setLog(new AndroidLog(LOG_KEY))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        MovieService service = restAdapter.create(MovieService.class);

        MovieResults results = service.discoverMovies(MovieService.SORT_BY_POPULARITY, apiKey);

        assertNotNull("results is null", results);
        assertEquals(1, results.page);
        assertTrue(results.results.length > 0);

        Movie movie = results.results[0];
        assertTrue(movie.id > 0);
        assertNotNull(movie.title);
    }
}
