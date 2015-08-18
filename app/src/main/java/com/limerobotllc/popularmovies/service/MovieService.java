package com.limerobotllc.popularmovies.service;

import com.limerobotllc.popularmovies.model.MovieResults;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface MovieService
{
    public String SORT_BY_POPULARITY = "popularity.desc";
    public String SORT_BY_HIGHEST_RATED = "vote_average.desc";

    @GET("/3/discover/movie")
    MovieResults discoverMovies(@Query("sort_by") String sortby, @Query("api_key") String apikey);

    @GET("/3/discover/movie")
    void discoverMovies(@Query("sort_by") String sortby, @Query("api_key") String apikey, Callback<MovieResults> callback);
}
