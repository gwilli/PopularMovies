package com.limerobotllc.popularmovies;

import android.content.Context;
import android.widget.ArrayAdapter;

public class SpinnerSortAdapter extends ArrayAdapter<String>
{
    final String[] keyValues;
    public SpinnerSortAdapter(Context context, int resource)
    {
        super(context, resource, context.getResources().getStringArray(R.array.sort_display_values));
        keyValues = context.getResources().getStringArray(R.array.sort_key_values);
    }

    public String getKeyValue(int position)
    {
        return keyValues[position];
    }
}
