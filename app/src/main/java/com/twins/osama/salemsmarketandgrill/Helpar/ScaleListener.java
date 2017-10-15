package com.twins.osama.salemsmarketandgrill.Helpar;

import android.content.Context;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Osama on 10/6/2017.
 */

public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    float onScaleBegin = 0;
    float onScaleEnd = 0;
    ImageView imageView;
    Context context;
    private float scale = 1f;

    public  ScaleListener(Context context, ImageView imageView) {
        this.imageView=imageView;
        this.context=context;
    }
    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        scale *= detector.getScaleFactor();
        imageView.setScaleX(scale);
        imageView.setScaleY(scale);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {

        Toast.makeText(context,"Scale Begin" ,Toast.LENGTH_SHORT).show();
        onScaleBegin = scale;

        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

        Toast.makeText(context,"Scale Ended",Toast.LENGTH_SHORT).show();
        onScaleEnd = scale;

        if (onScaleEnd > onScaleBegin){
            Toast.makeText(context,"Scaled Up by a factor of  " + String.valueOf( onScaleEnd / onScaleBegin ), Toast.LENGTH_SHORT  ).show();
        }

        if (onScaleEnd < onScaleBegin){
            Toast.makeText(context,"Scaled Down by a factor of  " + String.valueOf( onScaleBegin / onScaleEnd ), Toast.LENGTH_SHORT  ).show();
        }

        super.onScaleEnd(detector);
    }
}

