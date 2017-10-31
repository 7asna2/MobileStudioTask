package com.example.hasnaa.mobilestudiotask.NewPackage;

/**
 * Created by Hasnaa on 27-10-2017.
 */

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.IOException;


import com.example.hasnaa.mobilestudiotask.Modules.Result;
import com.example.hasnaa.mobilestudiotask.Utils;
import com.google.gson.internal.Primitives;

import java.util.List;

/**
 * Created by hasna2 on 23-Apr-16.
 */


public class FetchDataMovie<D> extends AsyncTaskLoader<List<D>> {

    // U result , D movie

    final String LOG_TAG = FetchDataMovie.class.getSimpleName();
    private New result;

    public FetchDataMovie(New<D> result ,Context context) {
        super(context);
        this.result=result;

//        CLASS_TYPE = class_type;
    }

    @Override
    public List<D> loadInBackground() {
        // TODO  get data from DB first and display it then refresh
        // if no data from server display latest response from DB
        try {

            DataTask dataTask = new DataTask(Utils.getPopularMoviesUrl());
            result = dataTask.getDataParsed(result.getClass(),DataTask.JSON_DATA_FORM);
            return result.getResult();


        } catch (IOException e) {
            Log.e(LOG_TAG, e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
    @Override
    protected void onStartLoading() {
            forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();


    }


}
