package com.example.kiragu.moviehub.Ui.Ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kiragu.moviehub.R;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

//        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
//                getSupportFragmentManager(), FragmentPagerItems.with(this)
//                .add(R.string.titleA, PageFragment.class)
//                .add(R.string.titleB, PageFragment.class)
//                .create());
//
//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setAdapter(adapter);
//
//        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
//        viewPagerTab.setViewPager(viewPager);
    }
}
