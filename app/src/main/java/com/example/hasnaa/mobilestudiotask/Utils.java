package com.example.hasnaa.mobilestudiotask;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Hasnaa on 27-10-2017.
 */
public class Utils {

    public static URL getPopularMoviesUrl () {

        URL url;
        try {
            final String themoviesdpURL = "http://api.themoviedb.org/3/movie";
            Uri uri = Uri.parse(themoviesdpURL)
                    .buildUpon()
                    .appendPath("popular")
                    .appendQueryParameter("api_key", BuildConfig.api_key)
                    .build();
            String myUrl = uri.toString();

            Log.v("getPopularMoviesUrl", "Build URI: " + myUrl);
            url = new URL(myUrl);

            return new URL(myUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e("error in url", e.getMessage());
            return null;
        }
    }


}
