package com.example.kiragu.moviehub.Ui.Ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.kiragu.moviehub.R;
import com.example.kiragu.moviehub.Ui.theMovieDbService;

import java.io.IOException;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = MovieListActivity.class.getSimpleName();

    @Bind(R.id.slider)
    SliderLayout mSlider;
    @Bind(R.id.queryEditText)
    EditText mQueryEditText;
    @Bind(R.id.submitButton)
    Button mSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("The Fast and Furious 8", "https://www.bellanaija.com/wp-content/uploads/2017/04/pd.jpg");
        url_maps.put("The Boss Baby", "https://i.ytimg.com/vi/tquIfapGVqs/maxresdefault.jpg");
        url_maps.put("Despicable me 3", "https://thebaytheatre.com/wp-content/uploads/2017/06/depicable-me-3-1496947425.jpg");
        url_maps.put("The Mummy", "http://www.dhakamovie.com/wp-content/uploads/2017/07/The-Mummy-2017-1080p-WEBRip.jpg");


        for(String name : url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mSlider.addSlider(textSliderView);

            Intent intent = getIntent();
            String query = intent.getStringExtra("movies");
            mSubmitButton.setOnClickListener(this);
        }
    }



//Using intents to pass the typed search query to MovieListActivity

@Override
public void onClick(View v){
    if (v == mSubmitButton){
        String query = mQueryEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, MovieListActivity.class);
        intent.putExtra("query", query);
        startActivity(intent);
    }

}

//    To prevent a memory leak on rotation, \we call stopAutoCycle() on the slider before activity or fragment is destroyed:
    @Override
    protected void onStop(){
        mSlider.stopAutoCycle();
        super.onStop();
    }



}