package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.model.MovieResults;
import com.limerobotllc.popularmovies.service.MovieService;
import com.limerobotllc.popularmovies.service.MovieServiceHelper;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MovieDiscoveryFragment extends Fragment implements Callback<MovieResults>
{
    private static final String LOG_TAG = MovieDiscoveryFragment.class.getSimpleName();
    private MovieResults movieResults = null;
    private GridView gridView = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.movie_discovery_fragment, container, false);

        if (savedInstanceState != null)
            movieResults = (MovieResults) savedInstanceState.getSerializable("movieResults");

        Movie[] movies = new Movie[0];
        if (movieResults == null)
            MovieServiceHelper.retrieveMovies(getActivity().getApplicationContext(), MovieService.SORT_BY_POPULARITY, this);
        else
            movies = movieResults.results;

        gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new MovieAdapter(getActivity(), 0, movies));
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putSerializable("movieResults", movieResults);
    }

    @Override
    public void success(MovieResults movieResults, Response response)
    {
        if (gridView != null && movieResults != null)
        {
            this.movieResults = movieResults;
            gridView.setAdapter(new MovieAdapter(getActivity(), 0, movieResults.results));
        }
    }

    @Override
    public void failure(RetrofitError error)
    {
        Log.e(LOG_TAG, error.getMessage());
        Toast.makeText(getActivity(), R.string.tmdb_server_error_msg, Toast.LENGTH_SHORT).show();
    }
}
