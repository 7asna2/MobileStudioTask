package com.example.hasnaa.mobilestudiotask;

/**
 * Created by Hasnaa on 27-10-2017.
 */

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.net.URL;


import com.example.hasnaa.mobilestudiotask.Modules.Movie;
import com.example.hasnaa.mobilestudiotask.Modules.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Created by hasna2 on 23-Apr-16.
 */


public class FetchDataMovie extends AsyncTaskLoader<List<Movie>> {


    final String LOG_TAG = FetchDataMovie.class.getSimpleName();


    public FetchDataMovie(Context context) {
        super(context);

    }

    @Override
    public List<Movie> loadInBackground() {
        // TODO  get data from DB first and display it then refresh
        // if no data from server display latest response from DB
        try {

            DataTask dataTask = new DataTask(Utils.getPopularMoviesUrl());
            Result result = dataTask.getDataParsed(Result.class ,DataTask.JSON_DATA_FORM);
            return result.getResults();

        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    @Override
    protected void onStartLoading() {
//        if (takeContentChanged())
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();


    }


}
