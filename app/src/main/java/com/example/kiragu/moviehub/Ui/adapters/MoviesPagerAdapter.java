package com.example.kiragu.moviehub.Ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kiragu.moviehub.Ui.fragments.MovieDetailFragment;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;

import java.util.ArrayList;

/**
 * Created by kiragu on 9/6/17.
 */

public class MoviesPagerAdapter extends FragmentPagerAdapter {
    public ArrayList<MovieSearch> mMovieSearch;

    public MoviesPagerAdapter(FragmentManager fm, ArrayList<MovieSearch> movieSearch){
        super(fm);
        mMovieSearch = movieSearch;
    }
    @Override
    public Fragment getItem(int position) {
        return MovieDetailFragment.newInstance(mMovieSearch.get(position));
    }

    @Override
    public int getCount() {
        return mMovieSearch.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMovieSearch.get(position).getTitle();
    }
}
