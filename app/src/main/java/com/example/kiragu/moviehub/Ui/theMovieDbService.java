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
    public static void searchMovie(String query, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.SEARCH_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString() + Constants.THE_MOVIE_Db_Api + Constants.SEARCH_BASE_ADDITION_URL + query;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }


//  Passing the results from the Api to MovieSearch Model

    public static ArrayList<MovieSearch> processResults(Response response) {

        ArrayList<MovieSearch> movieSearches = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject theMDbJSON = new JSONObject(jsonData);
                JSONArray searchMovieJSON = theMDbJSON.getJSONArray("results");

                try {
                    JSONArray multiSearchJSON = searchMovieJSON.optJSONObject(0).optJSONArray("known_for");
                    if (multiSearchJSON == null) {
                        for (int i = 0; i < searchMovieJSON.length(); i++) {
                            JSONObject movieJSON = searchMovieJSON.getJSONObject(i);
                            String title = movieJSON.optString("title");
                            String name = movieJSON.optString("name");
                            String overview = movieJSON.optString("overview");
                            String poster = movieJSON.optString("poster_path");
                            String releaseDate = movieJSON.optString("release_date");
                            Integer votes = movieJSON.optInt("vote_count");
                            MovieSearch movieSearch = new MovieSearch(title, name, overview, poster, releaseDate, votes);
                            movieSearches.add(movieSearch);
                        }
                    } else {
                        for (int i = 0; i < multiSearchJSON.length(); i++) {
                            JSONObject movieJSON1 = multiSearchJSON.getJSONObject(i);
                            String title = movieJSON1.optString("title");
                            String name = movieJSON1.optString("name");
                            String overview = movieJSON1.optString("overview");
                            String poster = movieJSON1.optString("poster_path");
                            String releaseDate = movieJSON1.optString("release_date");
                            Integer votes = movieJSON1.optInt("vote_count");
                            MovieSearch movieSearch1 = new MovieSearch(title, name, overview, poster, releaseDate, votes);
                            movieSearches.add(movieSearch1);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        return movieSearches;
    }
}