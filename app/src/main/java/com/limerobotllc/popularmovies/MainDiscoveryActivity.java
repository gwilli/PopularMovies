package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.limerobotllc.popularmovies.service.MovieService;

public class MainDiscoveryActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_discovery);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main_discovery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.menu_sort_popular)
        {
            sortMovies(MovieService.SORT_BY_POPULARITY);
            return true;
        }
        else if (id == R.id.menu_sort_rating)
        {
            sortMovies(MovieService.SORT_BY_HIGHEST_RATED);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sortMovies(String sortCriteria)
    {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.discovery_fragment);
        if (fragment != null && fragment instanceof MovieDiscoveryFragment)
            ((MovieDiscoveryFragment)fragment).resortMovies(sortCriteria);
    }
}
