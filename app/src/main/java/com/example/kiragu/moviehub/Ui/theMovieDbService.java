package com.example.kiragu.moviehub.Ui;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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


//  Passing the results from the Api to MovieSearch Model

    public static ArrayList<MovieSearch> processResults(Response response){

            ArrayList<MovieSearch> movieSearches = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject theMDbJSON = new JSONObject(jsonData);
                JSONArray searchMovieJSON = theMDbJSON.getJSONArray("results");
                for (int i = 0; i < searchMovieJSON.length(); i++) {
                    JSONObject movieJSON = searchMovieJSON.getJSONObject(i);
                    String title = movieJSON.getString("title");
                    String overview = movieJSON.getString("overview");
                    String poster = movieJSON.getString("poster_path");
                    String releaseDate = movieJSON.getString("release_date");
//                    ArrayList<Integer> genre = new ArrayList<>();
//                    JSONArray genreJSON = movieJSON.getJSONArray("genre_ids");
//
//                    for (int y = 0; y < genreJSON.length(); y++) {
//                        genre.add(movieJSON.getJSONObject(0));
//                    }
                    Integer votes = movieJSON.optInt("vote_count");

                    MovieSearch movieSearch = new MovieSearch(title, overview, poster, releaseDate, votes);
                    movieSearches.add(movieSearch);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieSearches;
    }
}
