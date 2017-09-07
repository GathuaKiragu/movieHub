package com.example.kiragu.moviehub.Ui.Ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.adapters.MovieListAdapter;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.example.kiragu.moviehub.Ui.theMovieDbService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import dmax.dialog.SpotsDialog;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private MovieListAdapter mMovieListAdapter;
    public static final String TAG = MovieListActivity.class.getSimpleName();
    public ArrayList<MovieSearch> mMovieSearch = new ArrayList<>();
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        ButterKnife.bind(this);

//   Receiving passed query from the main activity class
        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        progress = ProgressDialog.show(this, "MovieHub",
                "Loading...", true);
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
                        progress.show();
                        mMovieListAdapter = new MovieListAdapter(getApplicationContext(), mMovieSearch);
                        mRecyclerView.setAdapter(mMovieListAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MovieListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        if (progress.isShowing()) {
                            progress.dismiss();
                        }
                    }
                });
            }
        });
    }
}