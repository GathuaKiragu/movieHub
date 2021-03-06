package com.example.kiragu.moviehub.Ui.Ui;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.AppStatus;
import com.example.kiragu.moviehub.Ui.adapters.UpcomingMoviesAdapters;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.example.kiragu.moviehub.Ui.service.upcomingService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

        @Bind(R.id.slider)
        SliderLayout mSlider;


        // Navigation Drawer
        private DrawerLayout mDrawerLayout;
        private ActionBarDrawerToggle mToggle;

        public static final String TAG = MovieListActivity.class.getSimpleName();
        public ArrayList<MovieSearch> mMovieSearch = new ArrayList<>();
        private ProgressDialog progress;
        private UpcomingMoviesAdapters mUpcoming;
        @Bind(R.id.recyclerView2)
        RecyclerView mRecyclerView2;




        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                ButterKnife.bind(this);

                HashMap<String, String> url_maps = new HashMap<String, String>();
                url_maps.put("The Fast and Furious 8", "https://www.bellanaija.com/wp-content/uploads/2017/04/pd.jpg");
                url_maps.put("The Boss Baby", "https://i.ytimg.com/vi/tquIfapGVqs/maxresdefault.jpg");
                url_maps.put("Despicable me 3", "https://thebaytheatre.com/wp-content/uploads/2017/06/depicable-me-3-1496947425.jpg");
                url_maps.put("The Mummy", "http://www.dhakamovie.com/wp-content/uploads/2017/07/The-Mummy-2017-1080p-WEBRip.jpg");


                for (String name : url_maps.keySet()) {
                        TextSliderView textSliderView = new TextSliderView(this);
                        // initialize a SliderLayout
                        textSliderView
                                .description(name)
                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.Fit);

                        //add your extra information
                        textSliderView.bundle(new Bundle());
                        textSliderView.getBundle()
                                .putString("extra", name);

                        mSlider.addSlider(textSliderView);
                }
//  Navigation

                mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                mDrawerLayout.addDrawerListener(mToggle);
                mToggle.syncState();

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
                navigationView.setNavigationItemSelectedListener(this);


                if (AppStatus.getInstance(this).isOnline()) {
                        progress = ProgressDialog.show(this, "MovieHub",
                                "Loading...", true);
                } else {
                        Intent intent = new Intent(MainActivity.this, ErrorPage.class);
                        startActivity(intent);
                }
                getUpcomingMovies();
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                if (mToggle.onOptionsItemSelected(item)) {
                        return true;
                }
                return super.onOptionsItemSelected(item);

        }


//Using intents to pass the typed search query to MovieListActivity

//@Override
//public void onClick(View v){
//    if (v == mSubmitButton){
//
//    }

//}

        //    To prevent a memory leak on rotation, \we call stopAutoCycle() on the slider before activity or fragment is destroyed:
        @Override
        protected void onStop() {
                mSlider.stopAutoCycle();
                super.onStop();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.option_menu, menu);
                final MenuItem menuItem = menu.findItem(R.id.search);

                SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);


                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                        @Override
                        public boolean onQueryTextSubmit(String query) {
                                Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
                                intent.putExtra("query", query);
                                startActivity(intent);
                                return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                                return false;
                        }

                });
                return true;
        }

        @SuppressWarnings("StatementWithEmptyBody")
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                String[] category = {"28", "16", "35", "99", "18", "27", "878"};

                if (id == R.id.nav_home) {
                        Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                        MainActivity.this.startActivity(myIntent);

                } else if (id == R.id.nav_action) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[0]);
                        startActivity(myIntent);

                } else if (id == R.id.nav_animation) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[1]);
                        startActivity(myIntent);

                } else if (id == R.id.nav_comedy) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[2]);
                        startActivity(myIntent);

                } else if (id == R.id.nav_documentary) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[3]);
                        startActivity(myIntent);

                } else if (id == R.id.nav_drama) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[4]);
                        startActivity(myIntent);

                } else if (id == R.id.nav_horror) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[5]);
                        startActivity(myIntent);

                } else if (id == R.id.nav_scifi) {
                        Intent myIntent = new Intent(MainActivity.this, MovieCategoriesActivity.class);
                        myIntent.putExtra("category", category[6]);
                        startActivity(myIntent);
                }
                return true;
        }

        private void getUpcomingMovies() {
                final upcomingService movieService = new upcomingService();

                movieService.upcomingMovies(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                                e.printStackTrace();
                        }
                        @Override

                        public void onResponse(Call call, Response response) throws IOException {
                                mMovieSearch = upcomingService.processResults(response);
                                MainActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                                progress.show();
                                                mUpcoming = new UpcomingMoviesAdapters(getApplicationContext(), mMovieSearch);
                                                mRecyclerView2.setAdapter(mUpcoming);
                                                RecyclerView.LayoutManager layoutManager =
                                                        new LinearLayoutManager(MainActivity.this);
                                                mRecyclerView2.setLayoutManager(layoutManager);
                                                mRecyclerView2.setHasFixedSize(true);
                                                if (progress.isShowing()) {
                                                        progress.dismiss();
                                                }
                                        }
                                });
                        }
                });
        }
}