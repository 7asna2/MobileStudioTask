package com.example.hasnaa.mobilestudiotask.UI;

import com.example.hasnaa.mobilestudiotask.Modules.Movie;

/**
 * Created by Hasnaa on 30-10-2017.
 */
public interface ActivityListener {

    public final int MOBILE_DEVICE=100;
    public final int TABLET_DEVICE=101;


    int getDeviceType();
    void openMovieDetail(Movie movie);

}
