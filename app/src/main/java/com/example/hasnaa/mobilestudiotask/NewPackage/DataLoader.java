package com.example.hasnaa.mobilestudiotask.NewPackage;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;

import com.example.hasnaa.mobilestudiotask.Modules.Movie;

import java.net.URL;
import java.util.List;

/**
 * Created by Hasnaa on 30-10-2017.
 */

public abstract class DataLoader<D> {


//    public interface LoaderCallbacks{
//        abstract void OnCreateLoader(URL url);  //implement this method to modify your UI
//
//        abstract void OnNoDataAvailable(); // in case no data from internet or data base
//    }


    private static final int CURSOR_LOADER_ID = 0;
    private static final int SERVER_LOADER_ID = 1;
//    LoaderCallbacks callbacks;
    URL url;
    AppCompatActivity activity;
    Context context;
    New<D> result;

    private ConnectionDetector connectionDetector;

    public DataLoader(New<D> result, URL url,AppCompatActivity activity, Context context) {
        this.context = context;
        this.result = result;
        this.activity=activity;
//        this.callbacks=callbacks;
        connectionDetector = new ConnectionDetector(context);
        initLoaders();

    }


    public void setUrl(URL url) {
        this.url = url;
    }


    private LoaderManager.LoaderCallbacks<List<D>> serverDataLoader = new LoaderManager.LoaderCallbacks<List<D>>() {


        @Override
        public Loader<List<D>> onCreateLoader(int id, Bundle args) {
           OnCreateLoader(url);
            return new FetchDataMovie(result, context);
        }

        @Override
        public void onLoadFinished(Loader<List<D>> loader, List<D> data) {
            OnLoadFinished(data);
        }

        @Override
        public void onLoaderReset(Loader<List<D>> loader) {

        }
    };

//    private LoaderManager.LoaderCallbacks<Cursor> dBLoader = new LoaderManager.LoaderCallbacks<Cursor>() {
//
//
//        @Override
//        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//            return null;
//        }
//
//        @Override
//        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//
//        }
//
//        @Override
//        public void onLoaderReset(Loader<Cursor> loader) {
//
//        }
//
//
//    };

    private void initLoaders() {

        // check to get data from internet
        // then you can also check at database
        // else notify no data found
        if (connectionDetector.isConnectingToInternet()) {
            LoaderManager loaderManager = activity.getSupportLoaderManager();
            Loader<List<D>> loader = loaderManager.getLoader(SERVER_LOADER_ID);

            if (loader == null) {
                loaderManager.initLoader(SERVER_LOADER_ID, null, serverDataLoader);
            } else {
                loaderManager.restartLoader(SERVER_LOADER_ID, null, serverDataLoader);
            }
        } else
//            callbacks.OnNoDataAvailable();
            OnNoDataAvailable();

    }


    public void RefreshLoader() {
        initLoaders();
    }

    public abstract void OnCreateLoader(URL url);  //implement this method to modify your UI

    public abstract void OnLoadFinished(List<D> data); //implement this method to modify your UI

    public abstract void OnNoDataAvailable(); // in case no data from internet or data base

}
