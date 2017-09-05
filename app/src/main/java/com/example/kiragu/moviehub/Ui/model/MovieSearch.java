package com.example.kiragu.moviehub.Ui.model;

import com.example.kiragu.moviehub.Ui.Ui.MovieListActivity;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kiragu on 9/5/17.
 */

public class MovieSearch {
    private String mTitle;
    private String mOverview;
    private String mPoster;
    private String mReleaseDate;
    private Integer mVotes;
//    private ArrayList<Integer> mGenre = new ArrayList<>();

    public MovieSearch(String title, String overview, String poster, String releaseDate, Integer votes){
        this.mTitle = title;
        this.mOverview = overview;
        this.mPoster = poster;
        this.mReleaseDate = releaseDate;
        this.mVotes = votes;
//        this.mGenre = genre;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getOverview(){
        return mOverview;
    }
    public String getPoster(){
        return mPoster;
    }
    public String getReleaseDate(){
        return mReleaseDate;
    }
    public Integer getVotes(){
        return mVotes;
    }
//    public ArrayList<Integer> getGenre(){
//        return mGenre;
//    }
}
