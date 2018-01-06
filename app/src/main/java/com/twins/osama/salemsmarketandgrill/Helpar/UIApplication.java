package com.twins.osama.salemsmarketandgrill.Helpar;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by iSaleem on 11/29/17.
 */

public class UIApplication extends Application {


    public static UIApplication anInstance = null;
    RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        /************ Initial Volley ****************/
        anInstance = this;
        requestQueue = Volley.newRequestQueue(this);
        /************ Fresco **********************/
        Fresco.initialize(this);
        /*********** Realm Configuration *********/
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public synchronized void addToRequestQueue(Request request) {
        getRequestQueue().add(request);
    }

    public void cancel(String tag) {
        requestQueue.cancelAll(tag);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public static UIApplication getAnInstance() {
        return anInstance;
    }
//    public ImageLoader getImageLoader(){
//        return new ImageLoader(requestQueue,new MyImageLoader());
//    }
}
