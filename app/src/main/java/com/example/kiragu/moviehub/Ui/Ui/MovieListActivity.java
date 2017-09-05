package com.example.kiragu.moviehub.Ui.Ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.theMovieDbService;

import java.io.IOException;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieListActivity extends AppCompatActivity {
    public static final String TAG = MovieListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

//   Receiving passed query from the main activity class
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        getMovies(query);
    }

    //    Creating another instance of MovieDbService that will recieve requests
    private void getMovies(String query) {
        final theMovieDbService movieService = new theMovieDbService();
        movieService.searchMovie(query, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException{
                try {
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
