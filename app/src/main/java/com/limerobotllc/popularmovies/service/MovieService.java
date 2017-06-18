package com.limerobotllc.popularmovies.service;

import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.model.MovieResults;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface MovieService
{
    @GET("/3/discover/movie")
    MovieResults discoverMovies(@Query("sort_by") String sortby, @Query("api_key") String apikey);

    @GET("/3/discover/movie")
    void discoverMovies(@Query("sort_by") String sortby, @Query("api_key") String apikey, Callback<MovieResults> callback);

    @GET("/3/movie/{movie_id}")
    Movie getMovie(@Path("movie_id") long movieId, @Query("api_key") String apikey);

    @GET("/3/movie/{movie_id}")
    void getMovie(@Path("movie_id") long movieId, @Query("api_key") String apikey, Callback<Movie> callback);
}
