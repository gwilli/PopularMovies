package com.limerobotllc.popularmovies;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainDiscoveryActivity extends AppCompatActivity
{
    private Spinner spinner;
    private int sortSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_discovery);

        if (savedInstanceState != null)
            sortSelection = savedInstanceState.getInt("sortSelection", 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main_discovery, menu);

        MenuItem item = menu.findItem(R.id.sort_spinner);
        spinner = (Spinner) MenuItemCompat.getActionView(item);
        final SpinnerSortAdapter adapter = new SpinnerSortAdapter(this, R.layout.menu_spinner_line);
        spinner.setAdapter(adapter);
        spinner.setSelection(sortSelection);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                sortMovies(adapter.getKeyValue(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("sortSelection", spinner.getSelectedItemPosition());
    }

    private void sortMovies(String sortCriteria)
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.discovery_fragment);
        if (fragment != null && fragment instanceof MovieDiscoveryFragment)
            ((MovieDiscoveryFragment)fragment).retrieveMovies(sortCriteria);
    }
}
