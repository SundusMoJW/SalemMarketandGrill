package com.twins.osama.salemsmarketandgrill.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.VolleyRequests;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONArray;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.STATUS_SHARED_PREF;

public class Splash extends AppCompatActivity {
    SharedPrefUtil sharedPrefUtil;
    private Realm realm;
    ArrayList<Slider> list = new ArrayList<>();
    ArrayList<TypeList> typeList = new ArrayList<>();
    private ArrayList<Meals> meals = new ArrayList<>();
    private ArrayList<Market> market = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash);
        Realm.init(getApplication());
        realm = Realm.getDefaultInstance();
        fillImage();
        getTypeList();
        getMealsList();
        getMarketList();
        getNewstList();
        getEventstList();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Login login = new Login();
                sharedPrefUtil = new SharedPrefUtil(getApplicationContext());

                if (sharedPrefUtil.getBoolean(STATUS_SHARED_PREF)) {
                    startActivity(new Intent(Splash.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(Splash.this, Login.class));
                    finish();
                }
            }
        }, 2000);
    }

    public void fillImage() {
        final long lastUpdate = RealmController.with(Splash.this).lastUpdateSlider();
        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                RealmController.with(Splash.this).putSliderImage((JSONArray) o);
            }
        }).getSliderImage(lastUpdate);
    }

    public void getTypeList() {
        final long lastUpdate = RealmController.with(Splash.this).lastUpdateTypeList();
        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                RealmController.with(Splash.this).putTypeList((JSONArray) o);
            }
        }).getTypeList(lastUpdate);
    }

    public void getMealsList() {
        final long lastUpdate = RealmController.with(Splash.this).lastUpdateMeals();
        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                RealmController.with(Splash.this).putMealsList((JSONArray) o);
            }
        }).getMealsList(lastUpdate);
    }

    public void getMarketList() {
        final long lastUpdate = RealmController.with(Splash.this).lastUpdateMarket();
        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                RealmController.with(Splash.this).putMarketList((JSONArray) o);
            }
        }).getMarketList(lastUpdate);
    }

    public void getNewstList() {
        final long lastUpdate = RealmController.with(Splash.this).lastUpdateNews();
        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                RealmController.with(Splash.this).putNewsList((JSONArray) o);
            }
        }).getNewstList(lastUpdate);
    }

    public void getEventstList() {
        final long lastUpdate = RealmController.with(Splash.this).lastUpdateEvents();
        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                Log.d("***StatusEventstList", ((JSONArray)o).toString());

                RealmController.with(Splash.this).putEventsList((JSONArray) o);
            }
        }).getEventstList(lastUpdate);
    }
}
