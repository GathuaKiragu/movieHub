package com.example.kiragu.moviehub.Ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.service.CategoriesService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MovieCategoriesActivity extends AppCompatActivity {
    public static final String TAG = MovieCategoriesActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_movies);
        //   Receiving passed query from the main activity class
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        getCategory(category);
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
