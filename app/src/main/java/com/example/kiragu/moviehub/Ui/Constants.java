package com.example.kiragu.moviehub.Ui;

import com.example.kiragu.moviehub.BuildConfig;

/**
 * Created by kiragu on 9/4/17.
 */

public class Constants {
    public static final String THE_MOVIE_Db_Api = BuildConfig.MOVIE_Api;
    public static final String LATEST__MOVIES_BASE_URL = "https://api.themoviedb.org/4/list/1?api_key=";
    public static final String GENRE_BASE_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=";

//    https://api.themoviedb.org/3/search/movie?api_key={{Api_key}}&language=en-US&query=Naked
    public static final String SEARCH_BASE_URL = "https://api.themoviedb.org/3/search/movie?api_key=";
    public static final String SEARCH_BASE_ADDITION_URL = "&language=en-US&query=";
    public static final String SEARCH_QUERY ="query";
}
