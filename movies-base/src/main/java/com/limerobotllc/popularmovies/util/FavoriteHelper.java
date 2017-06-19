package com.limerobotllc.popularmovies.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.limerobotllc.popularmovies.model.Movie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoriteHelper
{
    private static final String USER_PREFS = "user_prefs";
    private static final String PREF_FAVORITES = "user_pref_favorites";

    private static Map<Long, Movie> getFavoriteMovies(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        String prefsJson = prefs.getString(PREF_FAVORITES, null);
        if (prefsJson == null)
            return new HashMap<>();

        return new Gson().fromJson(prefsJson, new TypeToken<Map<Long, Movie>>(){}.getType());
    }

    private static void saveFavoriteMovies(Context context, Map<Long, Movie> favoriteMovies)
    {
        SharedPreferences prefs = context.getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_FAVORITES, new Gson().toJson(favoriteMovies));
        editor.commit();
    }

    public static void addMovieToFavorites(Context context, Movie movie)
    {
        Map<Long, Movie> favorites = getFavoriteMovies(context);
        favorites.put(movie.id, movie);
        saveFavoriteMovies(context, favorites);
    }

    public static List<Movie> getAllMovieFavorites(Context context)
    {
        Map<Long, Movie> movieMap = getFavoriteMovies(context);
        return new ArrayList<>(movieMap.values());
    }

    public static boolean isMovieAFavorite(Context context, Movie movie)
    {
        Map<Long, Movie> movieMap = getFavoriteMovies(context);
        return movieMap.containsKey(movie.id);
    }

    public static void removeFromFavorites(Context context, Movie movie)
    {
        Map<Long, Movie> movieMap = getFavoriteMovies(context);
        movieMap.remove(movie.id);
        saveFavoriteMovies(context, movieMap);
    }
}
