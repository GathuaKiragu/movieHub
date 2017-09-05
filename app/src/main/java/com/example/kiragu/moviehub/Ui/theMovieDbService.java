package com.example.kiragu.moviehub.Ui;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by kiragu on 9/4/17.
 */
public class theMovieDbService {
    public static void searchMovie(String query, Callback callback){
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.SEARCH_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString() + Constants.THE_MOVIE_Db_Api + Constants.SEARCH_BASE_ADDITION_URL + query;

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    
}
