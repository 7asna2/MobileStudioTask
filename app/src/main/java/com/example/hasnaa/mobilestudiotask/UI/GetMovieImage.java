package com.example.hasnaa.mobilestudiotask.UI;

import android.widget.ImageView;

import com.example.hasnaa.mobilestudiotask.Modules.Movie;
import com.example.hasnaa.mobilestudiotask.SaveAndGetImages;

/**
 * Created by Hasnaa on 30-10-2017.
 */
public class GetMovieImage extends SaveAndGetImages {
    private Movie movie;

    public GetMovieImage(Movie movie, ImageView imageView) {
        super(movie.getImageURL(), imageView);
        this.movie = movie;
    }

    public void loadImage() {
        super.loadImage(movie.getId());
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}
