package com.limerobotllc.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.limerobotllc.popularmovies.model.Movie;

public class MovieDetailFragmentActivity extends AppCompatActivity
{
    public static final String PARAM_MOVIE = "param_movie";

    public static Intent getLaunchIntent(Context context, Movie movie)
    {
        Intent intent = new Intent(context, MovieDetailFragmentActivity.class);
        intent.putExtra(PARAM_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        if (savedInstanceState == null)
        {
            Movie movie = (Movie) getIntent().getSerializableExtra(PARAM_MOVIE);
            MovieDetailFragment fragment = new MovieDetailFragment();
            Bundle args = new Bundle();
            args.putSerializable(PARAM_MOVIE, movie);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, fragment).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings)
        {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
