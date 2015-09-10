package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.util.FavoriteHelper;
import com.squareup.picasso.Picasso;

import static com.limerobotllc.popularmovies.MovieDetailFragmentActivity.PARAM_MOVIE;
import static com.limerobotllc.popularmovies.service.MovieServiceHelper.getPosterImageUrl;
import static com.limerobotllc.popularmovies.util.FavoriteHelper.isMovieAFavorite;
import static com.limerobotllc.popularmovies.util.ViewHelper.setTextView;

/**
 * Fragment to display the movie details.
 */
public class MovieDetailFragment extends Fragment
{
    private Movie movie;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Bundle bundle = getArguments();
        movie = (Movie) bundle.getSerializable(PARAM_MOVIE);

        View v = inflater.inflate(R.layout.movie_detail, container, false);
        setMovieDetails(v);
        return v;
    }

    private void setMovieDetails(View v)
    {
        setTextView(v, R.id.movie_title, movie.title);
        setTextView(v, R.id.movie_rating, getString(R.string.movie_rating, movie.voteAverage));
        setTextView(v, R.id.movie_year, getMovieYear(movie.releaseDateString));
        setTextView(v, R.id.movie_synopsis, movie.overview);

        Picasso.with(getActivity())
                .load(getPosterImageUrl(getActivity(), movie.posterPath, getString(R.string.image_size_detail)))
                .into((ImageView) v.findViewById(R.id.movie_poster_image));

        Button addToFavoritesButton = (Button) v.findViewById(R.id.favorite_button);
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
}
