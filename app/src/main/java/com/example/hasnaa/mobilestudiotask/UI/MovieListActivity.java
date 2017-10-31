package com.example.hasnaa.mobilestudiotask.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.example.hasnaa.mobilestudiotask.Modules.Result;
import com.example.hasnaa.mobilestudiotask.NewPackage.DataLoader;
import com.example.hasnaa.mobilestudiotask.NewPackage.FetchDataMovie;
import com.example.hasnaa.mobilestudiotask.Modules.Movie;
import com.example.hasnaa.mobilestudiotask.MoviesRecyclerViewAdapter;
import com.example.hasnaa.mobilestudiotask.NewPackage.New;
import com.example.hasnaa.mobilestudiotask.R;
import com.example.hasnaa.mobilestudiotask.Utils;

import java.net.URL;
import java.util.List;

/**
 * An activity representing a list of Movies. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MovieDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class MovieListActivity extends AppCompatActivity implements ActivityListener{//, DataLoader<Movie>.LoaderCallbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    private RecyclerView recyclerView;
    private TextView emptyView;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    DataLoader<Movie> dataLoader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        emptyView=(TextView)findViewById(R.id.empty_view);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dataLoader.RefreshLoader();

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.movie_list);

        setupRecyclerView(recyclerView);

        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        setupLoader();

    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }



    public void setupLoader(){
        dataLoader = new DataLoader<Movie>(new Result(),Utils.getPopularMoviesUrl(),this,this) {

            @Override
            public void OnCreateLoader(URL url) {
                swipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void OnLoadFinished(List<Movie> data) {
                recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                if (recyclerView != null)
                    recyclerView.setAdapter(new MoviesRecyclerViewAdapter(MovieListActivity.this, data));
                recyclerView.getAdapter().notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void OnNoDataAvailable() {
                recyclerView.setVisibility(View.GONE);

                emptyView.setVisibility(View.VISIBLE);
            }
        };
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
            MovieDetailFragment fragment = new MovieDetailFragment();
            fragment.setActivityListener(this);
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT,movie);
            startActivity(intent);
        }
    }


}




















