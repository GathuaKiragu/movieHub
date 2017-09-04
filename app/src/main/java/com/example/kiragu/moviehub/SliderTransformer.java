package com.example.kiragu.moviehub;

import android.view.View;

import com.daimajia.slider.library.Transformers.AccordionTransformer;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.nineoldandroids.view.ViewHelper;

import static com.example.kiragu.moviehub.R.id.slider;


/**
 * Created by kiragu on 9/4/17.
 */

public class SliderTransformer extends BaseTransformer {
    @Override
    protected void onTransform(View view, float position) {
        ViewHelper.setPivotX(view, position < 0 ? 0 : view.getWidth());
        ViewHelper.setScaleX(view, position < 0 ? 1f + position : 1f - position);
//        slider.setPagerTransformer(false,new SliderTransformer());

    }
}
