package com.limerobotllc.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.limerobotllc.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import static com.limerobotllc.popularmovies.service.MovieServiceHelper.getPosterImageUrl;

public class MovieAdapter extends ArrayAdapter<Movie>
{
    public MovieAdapter(Context context, Movie[] movies)
    {
        super(context, 0, movies);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        ViewHolder holder;
        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.movie_poster_layout, parent, false);
            holder = new ViewHolder();
            holder.posterImage = (ImageView) view.findViewById(R.id.poster_image);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        final Movie selectedMovie = getItem(position);
        Picasso.with(getContext())
                .load(getPosterImageUrl(getContext(), selectedMovie.posterPath, getContext().getString(R.string.image_size_main)))
                .into(holder.posterImage);

        holder.posterImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = MovieDetailFragmentActivity.getLaunchIntent(getContext(), selectedMovie.id);
                getContext().startActivity(intent);
            }
        });

        return view;
    }

    static class ViewHolder
    {
        ImageView posterImage;
    }
}
