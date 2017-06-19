package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.model.MovieResults;
import com.limerobotllc.popularmovies.service.MovieServiceHelper;
import com.limerobotllc.popularmovies.util.FavoriteHelper;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDiscoveryFragment extends Fragment implements Callback<MovieResults>
{
    private static final String LOG_TAG = MovieDiscoveryFragment.class.getSimpleName();
    private MovieResults movieResults = null;
    private GridView gridView = null;
    private String sortCriteria;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null)
        {
            movieResults = (MovieResults) savedInstanceState.getSerializable("movieResults");
            sortCriteria = savedInstanceState.getString("sortCriteria");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.movie_discovery_fragment, container, false);

        gridView = (GridView) view.findViewById(R.id.gridview);
        Movie[] results = movieResults != null ? movieResults.results : new Movie[0];
        gridView.setAdapter(new MovieAdapter(getActivity(), results));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("movieResults", movieResults);
        outState.putString("sortCriteria", sortCriteria);
    }

    @Override
    public void success(MovieResults movieResults, Response response)
    {
        if (gridView != null && movieResults != null)
        {
            this.movieResults = movieResults;
            gridView.setAdapter(new MovieAdapter(getActivity(), movieResults.results));
        }
    }

    @Override
    public void failure(RetrofitError error)
    {
        Log.e(LOG_TAG, error.getMessage());
        Toast.makeText(getActivity(), R.string.tmdb_server_error_msg, Toast.LENGTH_SHORT).show();
    }

    public void retrieveMovies(@NonNull String sortCriteria)
    {
        if (!sortCriteria.equals(this.sortCriteria))
        {
            if (sortCriteria.equals("favorites"))
            {
                List<Movie> movies = FavoriteHelper.getAllMovieFavorites(getActivity());
                MovieResults results = new MovieResults(1, movies.toArray(new Movie[movies.size()]));
                success(results, null);
            }
            else
            {
                MovieServiceHelper.retrieveMovies(getActivity().getApplicationContext(),
                        sortCriteria, this);
                this.sortCriteria = sortCriteria;
            }
        }
    }
}
