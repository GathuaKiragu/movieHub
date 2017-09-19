package com.example.kiragu.moviehub.Ui.Ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.adapters.CategoriesListAdapter;
import com.example.kiragu.moviehub.Ui.adapters.MovieListAdapter;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.example.kiragu.moviehub.Ui.service.CategoriesService;
import com.example.kiragu.moviehub.Ui.service.theMovieDbService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieCategoriesActivity extends AppCompatActivity {
    public static final String TAG = MovieCategoriesActivity.class.getSimpleName();
    private ProgressDialog progress;
    public ArrayList<MovieSearch> mCategoriesList = new ArrayList<>();
    private CategoriesListAdapter mCategoriesAdapter;
    @Bind(R.id.recyclerView1)
    RecyclerView mRecyclerView1;
    String[] names ={"Actions", "Animations", "Comedy", "Documentary", "Drama", "Horror", "Scifi"};

    String[] category = {"28", "16", "35", "99", "18", "27", "878"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_movies);
        ButterKnife.bind(this);
        //   Receiving passed query from the main activity class
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        progress = ProgressDialog.show(this, "MovieHub",
                "Loading...", true);
        getCategory(category);

            if (category == "28") {
                getSupportActionBar().setTitle("Action movies");
            } else if (category.equals("16")) {
                getSupportActionBar().setTitle("Animation movies");
            } else if (category.equals("35")) {
                getSupportActionBar().setTitle("Comedies");
            } else if (category.equals("99")){
                getSupportActionBar().setTitle("Documentaries");
            } else if (category.equals("18")){
                getSupportActionBar().setTitle("Drama movies");
            } else if (category.equals("27")) {
                getSupportActionBar().setTitle("Horror Movies");
            } else {
                getSupportActionBar().setTitle("Science Fiction");
            }
        }

    private void getCategory(String category) {
        final CategoriesService categoryService = new CategoriesService();
        categoryService.findCategory(category, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mCategoriesList = CategoriesService.processResults(response);
                MovieCategoriesActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        progress.show();
                        mCategoriesAdapter = new CategoriesListAdapter(getApplicationContext(), mCategoriesList);
                        mRecyclerView1.setAdapter(mCategoriesAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(MovieCategoriesActivity.this);
                        mRecyclerView1.setLayoutManager(layoutManager);
                        mRecyclerView1.setHasFixedSize(true);

                        if (progress.isShowing()) {
                            progress.dismiss();
                        }
                    }
                });
            }
        });
    }
}
