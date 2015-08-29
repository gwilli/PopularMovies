package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.limerobotllc.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import static com.limerobotllc.popularmovies.MovieDetailFragmentActivity.PARAM_MOVIE;
import static com.limerobotllc.popularmovies.service.MovieServiceHelper.getPosterImageUrl;

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
        ((TextView) v.findViewById(R.id.movie_title)).setText(movie.title);
        ((TextView) v.findViewById(R.id.movie_rating)).setText(getString(R.string.movie_rating, movie.voteAverage));
        ((TextView) v.findViewById(R.id.movie_year)).setText(getMovieYear(movie.releaseDateString));
        ((TextView) v.findViewById(R.id.movie_synopsis)).setText(movie.overview);
        Picasso.with(getActivity())
                .load(getPosterImageUrl(getActivity(), movie.posterPath, getString(R.string.image_size_small)))
                .into((ImageView)v.findViewById(R.id.movie_poster_image));
    }

    private String getMovieYear(String date)
    {
        if (date == null || date.isEmpty())
            return "";
        return date.split("-")[0];
    }
}
