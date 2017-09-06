package com.example.kiragu.moviehub.Ui.Ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.adapters.MoviesPagerAdapter;
import com.example.kiragu.moviehub.Ui.model.MovieSearch;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetails extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private MoviesPagerAdapter adapterViewPager;
    ArrayList<MovieSearch> mMovieSearch = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

//        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
//                getSupportFragmentManager(), FragmentPagerItems.with(this)
//                .add(R.string.titleA, PageFragment.class)
//                .add(R.string.titleB, PageFragment.class)
//                .create());
//
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setAdapter(adapter);
//
        mMovieSearch = Parcels.unwrap(getIntent().getParcelableExtra("movieSearch"));
        int startingPosition = getIntent().getIntExtra("position", 0);
        adapterViewPager = new MoviesPagerAdapter(getSupportFragmentManager(), mMovieSearch);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(mViewPager);
    }
}
