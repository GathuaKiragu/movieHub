package com.example.kiragu.moviehub.Ui;

import android.view.View;

import com.daimajia.slider.library.Animations.BaseAnimationInterface;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

import butterknife.Bind;

/**
 * Created by kiragu on 9/4/17.
 */

public class CustomAnimation implements BaseAnimationInterface {


// When the current item is about to leave the screen we make it dissapear.
    @Override
    public void onPrepareCurrentItemLeaveScreen(View current){
        View descriptionLayout = current.findViewById(com.daimajia.slider.library.R.id.description_layout);
        if(descriptionLayout != null){
            current.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);
        }

    }

// When the next item is about to appear then the description layout is hidden.

    @Override
    public void onPrepareNextItemShowInScreen(View next){
        View descriptionLayout = next.findViewById(com.daimajia.slider.library.R.id.description_layout);
        if (descriptionLayout != null) {
            next.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(View.INVISIBLE);
        }
    }

//    Animation to show the nextItem

    @Override
    public void onCurrentItemDisappear(View view){

    }
    @Override
    public void onNextItemAppear(View next){
        View descriptionLayout = next.findViewById(com.daimajia.slider.library.R.id.description_layout);
        if(descriptionLayout != null){
            float layoutY = ViewHelper.getY(descriptionLayout);
            next.findViewById(com.daimajia.slider.library.R.id.description_layout).setVisibility(next.VISIBLE);

            ValueAnimator animator = ObjectAnimator.ofFloat(
                    descriptionLayout,"y",layoutY + descriptionLayout.getHeight(),
                    layoutY).setDuration(500);
            animator.start();
        }
        }

    }

