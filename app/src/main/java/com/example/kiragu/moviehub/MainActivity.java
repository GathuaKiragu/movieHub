package com.example.kiragu.moviehub;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.slider)
    SliderLayout mSlider;
    @Bind(R.id.custom_indicator) PagerIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView.description("Narcos");
        textSliderView.image("http://cdn2-www.comingsoon.net/assets/uploads/2015/07/narcossocials.jpg");

        mSlider.addSlider(textSliderView);
        mSlider.setCustomIndicator(pageIndicator);
    }

//    To prevent a memory leak on rotation, \we call stopAutoCycle() on the slider before activity or fragment is destroyed:
    @Override
    protected void onStop(){
        mSlider.stopAutoCycle();
        super.onStop();
    }

}