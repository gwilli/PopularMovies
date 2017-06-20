package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.service.MovieServiceHelper;
import com.limerobotllc.popularmovies.util.FavoriteHelper;
import com.squareup.picasso.Picasso;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.limerobotllc.popularmovies.MovieDetailFragmentActivity.PARAM_MOVIE_ID;
import static com.limerobotllc.popularmovies.service.MovieServiceHelper.getPosterImageUrl;
import static com.limerobotllc.popularmovies.util.FavoriteHelper.isMovieAFavorite;
import static com.limerobotllc.popularmovies.util.ViewHelper.setTextView;

/**
 * Fragment to display the movie details.
 */
public class MovieDetailFragment extends Fragment implements Callback<Movie>
{
    private static final String LOG_TAG = MovieDetailFragment.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        long movieId = bundle.getLong(PARAM_MOVIE_ID);
        if (movieId == -1) {
            showNoMovieError();
        } else {
            MovieServiceHelper.retrieveMovie(getActivity().getApplicationContext(), movieId, this);
        }
    }

    private void showNoMovieError() {
        Toast.makeText(getActivity(), R.string.error_no_movie_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.movie_detail, container, false);
    }

    private void setMovieDetails(final Movie movie)
    {
        setTextView(getView(), R.id.movie_title, movie.title);
        setTextView(getView(), R.id.movie_rating, getString(R.string.movie_rating, movie.voteAverage));
        setTextView(getView(), R.id.movie_year, getMovieYear(movie.releaseDateString));
        setTextView(getView(), R.id.movie_synopsis, movie.overview);

        Picasso.with(getActivity())
                .load(getPosterImageUrl(getActivity(), movie.posterPath, getString(R.string.image_size_detail)))
                .into((ImageView) getView().findViewById(R.id.movie_poster_image));

        Button addToFavoritesButton = (Button) getView().findViewById(R.id.favorite_button);
        if (addToFavoritesButton != null)
            addToFavoritesButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v)
                {
                    boolean isFavorite = isMovieAFavorite(getActivity(), movie);
                    if (isFavorite)
                        FavoriteHelper.removeFromFavorites(getActivity(), movie);
                    else
                        FavoriteHelper.addMovieToFavorites(getActivity(), movie);
                    toggleFavoriteButton((Button)v, !isFavorite);
                }
            });
        toggleFavoriteButton(addToFavoritesButton, isMovieAFavorite(getActivity(), movie));
    }

    private String getMovieYear(String date)
    {
        if (date == null || date.isEmpty())
            return "";
        return date.split("-")[0];
    }

    private void toggleFavoriteButton(Button button, boolean isFavorite)
    {
        button.setText(isFavorite ? R.string.favorite_button_remove_favorite : R.string.favorite_button_set_favorite);
    }

    @Override
    public void success(Movie movie, Response response) {
        if (movie == null) {
            showNoMovieError();
        } else {
            setMovieDetails(movie);
        }
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e(LOG_TAG, error.getMessage());
        Toast.makeText(getActivity(), R.string.tmdb_server_error_msg, Toast.LENGTH_SHORT).show();
    }
}
