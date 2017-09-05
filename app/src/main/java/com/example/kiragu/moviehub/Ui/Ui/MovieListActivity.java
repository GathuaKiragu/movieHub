package com.example.kiragu.moviehub.Ui.Ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.example.kiragu.moviehub.Ui.theMovieDbService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieListActivity extends AppCompatActivity {
    @Bind(R.id.movieListview)
    ListView mListView;

    public static final String TAG = MovieListActivity.class.getSimpleName();
    public ArrayList<MovieSearch> mMovieSearch = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

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
            public void onResponse(Call call, Response response) throws IOException {
                mMovieSearch = theMovieDbService.processResults(response);

                MovieListActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] movieNames = new String[mMovieSearch.size()];
                        for (int i = 0; i < movieNames.length; i++) {
                            movieNames[i] = mMovieSearch.get(i).getTitle();

                            ArrayAdapter adapter = new ArrayAdapter(MovieListActivity.this,
                                    android.R.layout.simple_list_item_1, movieNames);
                            mListView.setAdapter(adapter);
                            for (MovieSearch movieSearched : mMovieSearch) {
                                Log.d(TAG, "Title: " + movieSearched.getTitle());
                                Log.d(TAG, "Description: " + movieSearched.getOverview());
                                Log.d(TAG, "Poster: " + movieSearched.getPoster());
                                Log.d(TAG, "Release Dates: " + movieSearched.getReleaseDate());
                                Log.d(TAG, "Votes" + movieSearched.getVotes());
                            }
                        }
                    }
                });
            }
        });
    }
}

