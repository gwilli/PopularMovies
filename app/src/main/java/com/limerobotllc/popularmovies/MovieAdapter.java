package com.limerobotllc.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.limerobotllc.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends ArrayAdapter<Movie>
{
    public MovieAdapter(Context context, int resource, Movie[] movies)
    {
        super(context, resource, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
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

        Picasso.with(getContext())
                .load(getPosterImageUrl(getItem(position).posterPath))
                .into(holder.posterImage);

        return view;
    }

    private String getPosterImageUrl(String posterpath)
    {
        if (posterpath == null)
            return null;

        String urlBase = getContext().getString(R.string.image_base_url);
        String size = getContext().getString(R.string.image_size);
        return urlBase + size + posterpath;
    }

    static class ViewHolder
    {
        ImageView posterImage;
    }
}
