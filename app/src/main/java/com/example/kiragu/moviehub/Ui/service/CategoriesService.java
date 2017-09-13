package com.example.kiragu.moviehub.Ui.service;

import com.example.kiragu.moviehub.Ui.Constants;
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
 * Created by kiragu on 9/12/17.
 */

public class CategoriesService {

    public static void findCategory(String category, Callback callback) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MOVIELIST_BASE_URL).newBuilder();
            String url = urlBuilder.build().toString() + category + Constants.MOVIELIST_2 + Constants.THE_MOVIE_Db_Api  + Constants.MOVIELIST_FINAL ;

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
                    JSONArray categories = theMDbJSON.getJSONArray("results");
                            for (int i = 0; i < categories.length(); i++) {
                                JSONObject categoryList = categories.getJSONObject(i);
                                String title = categoryList.optString("title");
                                String name = categoryList.optString("name");
                                String overview = categoryList.optString("overview");
                                String poster = categoryList.optString("poster_path");
                                String releaseDate = categoryList.optString("release_date");
                                Integer votes = categoryList.optInt("vote_count");
                                MovieSearch movieSearch = new MovieSearch(title, name, overview, poster, releaseDate, votes);
                                movieSearches.add(movieSearch);
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
