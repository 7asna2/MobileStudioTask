package com.example.hasnaa.mobilestudiotask.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;


import com.example.hasnaa.mobilestudiotask.UI.ActivityListener;
import com.example.hasnaa.mobilestudiotask.FetchDataMovie;
import com.example.hasnaa.mobilestudiotask.Modules.Movie;
import com.example.hasnaa.mobilestudiotask.MoviesRecyclerViewAdapter;
import com.example.hasnaa.mobilestudiotask.R;

import java.util.List;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Movie>>,ActivityListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private final static int MOVIES_LOADER = 101;
    RecyclerView recyclerView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        recyclerView = (RecyclerView) findViewById(R.id.movie_list);

//        assert recyclerView != null;
        setupRecyclerView(recyclerView);

        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        Refresh();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
//        recyclerView.setAdapter(new MoviesRecyclerViewAdapter( data));
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(int id, Bundle args) {
        Log.d("movie :", "loder starting");
        return new FetchDataMovie(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Movie>> loader, List<Movie> data) {
//        for(Movie m :data)
//            Log.v("movie :",m.getTitle());
        if (recyclerView != null)
            recyclerView.setAdapter(new MoviesRecyclerViewAdapter(this, data));
    }

    @Override
    public void onLoaderReset(Loader<List<Movie>> loader) {

    }


    public void Refresh() {
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<List<Movie>> loader = loaderManager.getLoader(MOVIES_LOADER);
        if (loader == null) {
            loaderManager.initLoader(MOVIES_LOADER, null, this);
        } else {
            loaderManager.restartLoader(MOVIES_LOADER, null, this);
        }
    }

    @Override
    public int getDeviceType() {
        if(mTwoPane) return TABLET_DEVICE;
        else return MOBILE_DEVICE;
    }

    @Override
    public void openMovieDetail(Movie movie) {
        if (getDeviceType() == TABLET_DEVICE) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(MovieDetailFragment.ARG_ITEM_ID,movie);
//                  arguments.putString(MovieDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setActivityListener(this);
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
//            Context context = v.getContext();
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT,movie);
//                  intent.putExtra(MovieDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
            startActivity(intent);
        }
    }
}




















