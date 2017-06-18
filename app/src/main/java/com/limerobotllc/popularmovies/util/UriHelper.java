package com.limerobotllc.popularmovies.util;

import android.net.Uri;
import android.util.Log;

/**
 * Created by greg on 6/18/17.
 */

public class UriHelper {
    private static final String LOG_TAG = UriHelper.class.getSimpleName();

    /**
     * Returns id from the last path segment or -1 if error.
     *
     * @param uri
     * @return
     */
    public static long getIdFromLastSegment(Uri uri) {
        long id = -1;

        try {
            id = Long.parseLong(uri.getLastPathSegment());
        } catch (NumberFormatException e) {
            Log.e(LOG_TAG, "Unable to parse id from uri: " + uri.toString());
        }

        return id;
    }
}
