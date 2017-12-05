package com.twins.osama.salemsmarketandgrill.Helpar;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by iSaleem on 11/29/17.
 */

public class UIApplication extends Application {


    public static UIApplication anInstance = null;
    RequestQueue requestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        anInstance = this;
        requestQueue = Volley.newRequestQueue(this);

        Fresco.initialize(this);
    }

    public synchronized void addToRequestQueue(Request request){
        getRequestQueue().add(request);
    }

    public void cancel(String tag){
        requestQueue.cancelAll(tag);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static UIApplication getAnInstance(){
        return anInstance;
    }
//    public ImageLoader getImageLoader(){
//        return new ImageLoader(requestQueue,new MyImageLoader());
//    }
}
