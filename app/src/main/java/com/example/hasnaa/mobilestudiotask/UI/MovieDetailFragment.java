package com.example.hasnaa.mobilestudiotask.UI;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hasnaa.mobilestudiotask.UI.ActivityListener;
import com.example.hasnaa.mobilestudiotask.Modules.Movie;
import com.example.hasnaa.mobilestudiotask.R;
import com.example.hasnaa.mobilestudiotask.UI.MovieDetailActivity;
import com.example.hasnaa.mobilestudiotask.UI.MovieListActivity;

/**
 * A fragment representing a single Movie detail screen.
 * This fragment is either contained in a {@link MovieListActivity}
 * in two-pane mode (on tablets) or a {@link MovieDetailActivity}
 * on handsets.
 */
public class MovieDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item";

    private Movie mMovie;
    private ActivityListener activityListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MovieDetailFragment() {
    }

    public void setActivityListener (ActivityListener activityListener){
        this.activityListener = activityListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mMovie = getArguments().getParcelable(ARG_ITEM_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.movie_detail, container, false);

        // Show the dummy content as text in a TextView.

        if (mMovie != null) {
            bindViews( rootView);
        }

        return rootView;
    }

    public void bindViews (View rootView){
        ((TextView) rootView.findViewById(R.id.movie_over_view)).setText(mMovie.getOverview());

        if(activityListener ==null) {
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            appBarLayout.setTitle(mMovie.getTitle());
            ImageView poster = ((ImageView) getActivity().findViewById(R.id.poster));
            (new GetMovieImage(mMovie,poster)).loadImage();
        }

        ((TextView) rootView.findViewById(R.id.date)).setText(mMovie.getReleaseDate());
        ((TextView) rootView.findViewById(R.id.language)).setText(mMovie.getOriginalLanguage());
        if(mMovie.getVoteAverage()!=null)
            ((RatingBar) rootView.findViewById(R.id.rating)).setRating(Float.parseFloat(mMovie.getVoteAverage())/2);
        else
            ((RatingBar) rootView.findViewById(R.id.rating)).setRating( 8/2);
        Log.v("rating",mMovie.getVoteAverage()+"");
        Log.v("rating",mMovie.getVoteCount()+"");


    }
}
