package com.limerobotllc.popularmovies.service;

import com.limerobotllc.popularmovies.model.MovieResults;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MovieService
{
    @GET("/3/discover/movie")
    MovieResults discoverMovies(@Query("sort_by") String sortby, @Query("api_key") String apikey);

    @GET("/3/discover/movie")
    void discoverMovies(@Query("sort_by") String sortby, @Query("api_key") String apikey, Callback<MovieResults> callback);
}
