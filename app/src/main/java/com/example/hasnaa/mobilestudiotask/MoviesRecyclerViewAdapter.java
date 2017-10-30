package com.example.hasnaa.mobilestudiotask;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hasnaa.mobilestudiotask.Modules.Movie;
import com.example.hasnaa.mobilestudiotask.UI.ActivityListener;
import com.example.hasnaa.mobilestudiotask.UI.GetMovieImage;

import java.util.List;

/**
 * Created by Hasnaa on 30-10-2017.
 */



public class MoviesRecyclerViewAdapter
        extends RecyclerView.Adapter<MoviesRecyclerViewAdapter.ViewHolder> {

    private final List<Movie> mMovies;
    private static ActivityListener activityListener;

    public MoviesRecyclerViewAdapter(ActivityListener activityListener, List<Movie> items) {
        mMovies = items;
        this.activityListener =activityListener;
//        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mMovies.get(position);

        GetMovieImage getMovieImage = new GetMovieImage(holder.mItem,holder.moviePoster);
        getMovieImage.loadImage();
//            Picasso.with(context).load(holder.mItem.getImageURL()).resize(640, 640).into(holder.moviePoster);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityListener.openMovieDetail(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        //            public final TextView mIdView;
        public final ImageView moviePoster;
        public Movie mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            moviePoster = (ImageView) view.findViewById(R.id.movie_poster);
        }

    }
}


