package com.limerobotllc.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.limerobotllc.popularmovies.model.Movie;
import com.limerobotllc.popularmovies.util.UriHelper;

public class MovieDetailFragmentActivity extends AppCompatActivity
{
    public static final String PARAM_MOVIE_ID = "movieId";

    public static Intent getLaunchIntent(Context context, long movieId)
    {
        Intent intent = new Intent(context, MovieDetailFragmentActivity.class);
        intent.setData(Uri.parse(context.getString(R.string.app_movie_detail_base_url) + movieId));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_activity);

        if (savedInstanceState == null)
        {
            Uri appLinkData = getIntent().getData();
            long movieId = UriHelper.getIdFromLastSegment(appLinkData);
            MovieDetailFragment fragment = new MovieDetailFragment();
            Bundle args = new Bundle();
            args.putLong(PARAM_MOVIE_ID, movieId);
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
