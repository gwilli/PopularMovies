package com.limerobotllc.popularmovies.service;

import android.content.Context;
import android.util.Log;

import com.limerobotllc.popularmovies.BuildConfig;
import com.limerobotllc.popularmovies.R;
import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.model.MovieResults;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;

public class MovieServiceHelper
{
    private static final String LOG_KEY = MovieServiceHelper.class.getSimpleName();

    public static void retrieveMovies(Context context, String sort, Callback<MovieResults> callback)
    {
        Log.v(LOG_KEY, "Retrieving movies by " + sort);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(context.getString(R.string.tmdb_endpoint_url))
                .setLog(new AndroidLog(LOG_KEY))
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();

        MovieService service = restAdapter.create(MovieService.class);

        service.discoverMovies(sort, context.getString(R.string.tmdb_api_key), callback);
    }

    public static String getPosterImageUrl(Context context, String posterpath, String size)
    {
        if (posterpath == null)
            return null;

        String urlBase = context.getString(R.string.image_base_url);
        return urlBase + size + posterpath;
    }

    public static void retrieveMovie(Context context, long movieId, Callback<Movie> callback)
    {
        Log.v(LOG_KEY, "Retrieving movie by id: " + movieId);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(context.getString(R.string.tmdb_endpoint_url))
                .setLog(new AndroidLog(LOG_KEY))
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();

        MovieService service = restAdapter.create(MovieService.class);
        service.getMovie(movieId, context.getString(R.string.tmdb_api_key), callback);
    }
}
