package com.twins.osama.salemsmarketandgrill.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.STATIC_URL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.STATUS_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URLF;

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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLF,null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                realm.beginTransaction();
//                RealmResults<Slider> result = realm.where(Slider.class).findAll();
//                if (!(result.isEmpty())) {
//                    result.deleteAllFromRealm();
//                    realm.commitTransaction();
//                } else realm.cancelTransaction();
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
                    boolean Status = response.optBoolean("Status");
                    if (Status) {
                        JSONArray jsonArray = response.optJSONArray("OtherData");
                        realm.beginTransaction();
                        realm.createOrUpdateAllFromJson(TypeList.class, response.optJSONArray("OtherData"));
                        realm.commitTransaction();
                        Log.i("OtherData", jsonArray.toString());
                        Log.i("response", response.toString());
                    } else {
                        Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();
                }
            });
        requestQueue.add(request);
        }

    public void getTypeList() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getTypeList", null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                if (Status) {
                    JSONArray jsonArray = response.optJSONArray("OtherData");
                    realm.beginTransaction();
                    realm.createOrUpdateAllFromJson(TypeList.class, response.optJSONArray("OtherData"));
                    realm.commitTransaction();
                    Log.i("OtherData", jsonArray.toString());
                    Log.i("response", response.toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    public void getMealsList() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMealsList", null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
//                realm.beginTransaction();
//                RealmResults<Meals> result = realm.where(Meals.class).findAll();
//                if (!(result.isEmpty())) {
//                    result.deleteAllFromRealm();
//                    realm.commitTransaction();
//                } else realm.cancelTransaction();
                if (Status) {
                    JSONArray jsonArray = response.optJSONArray("OtherData");
                    realm.beginTransaction();
                    realm.createOrUpdateAllFromJson(Meals.class, response.optJSONArray("OtherData"));
                    realm.commitTransaction();
                    Log.i("OtherData", jsonArray.toString());
                    Log.i("response", response.toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }

    public void getMarketList() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMarketList",null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
//                realm.beginTransaction();
//                RealmResults<Meals> result = realm.where(Meals.class).findAll();
//                if (!(result.isEmpty())) {
//                    result.deleteAllFromRealm();
//                    realm.commitTransaction();
//                } else realm.cancelTransaction();
                if (Status) {
                    JSONArray jsonArray = response.optJSONArray("OtherData");
                    realm.beginTransaction();
                    realm.createOrUpdateAllFromJson(Market.class, response.optJSONArray("OtherData"));
                    realm.commitTransaction();
                    Log.i("OtherData", jsonArray.toString());
                    Log.i("response", response.toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Error ", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }
}
