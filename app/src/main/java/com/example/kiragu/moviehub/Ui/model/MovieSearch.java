package com.example.kiragu.moviehub.Ui.model;

import com.example.kiragu.moviehub.Ui.Ui.MovieListActivity;

import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by kiragu on 9/5/17.
 */
@Parcel
public class MovieSearch {
    String mTitle;
    String mName;
    String mOverview;
    String mPoster;
    String mReleaseDate;
    Integer mVotes;
//    private ArrayList<Integer> mGenre = new ArrayList<>();
    public MovieSearch(String title, String name, String overview, String poster, String releaseDate, Integer votes){
        this.mTitle = title;
        this.mName = name;
        this.mOverview = overview;
        this.mPoster = poster;
        this.mReleaseDate = releaseDate;
        this.mVotes = votes;
//        this.mGenre = genre;
    }


    /**
     * Constructor for parceler
     */
    public MovieSearch(){

    }
    public String getTitle(){
        return mTitle;
    }
    public String getName(){
        return mName;
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
