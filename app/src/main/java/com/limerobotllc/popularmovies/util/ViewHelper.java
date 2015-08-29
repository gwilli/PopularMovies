package com.limerobotllc.popularmovies.util;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.TextView;

public class ViewHelper
{
    public static void setTextView(View parentView, @IdRes int resId, String value) {
        if (parentView != null) {
            TextView tv = (TextView) parentView.findViewById(resId);
            if (tv != null)
                tv.setText(value);
        }
    }
}
