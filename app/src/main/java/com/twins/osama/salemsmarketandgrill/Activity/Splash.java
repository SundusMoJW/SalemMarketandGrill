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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.crashlytics.android.Crashlytics;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.R;

import io.fabric.sdk.android.Fabric;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;
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
        Log.i("///", "///" + "");
//        final Realm realm = Realm.getDefaultInstance();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URLF
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("///", "///" + "" + response);
                realm.beginTransaction();
                RealmResults<Slider> result = realm.where(Slider.class).findAll();
                if (!(result.isEmpty())) {
                    result.deleteAllFromRealm();
                    realm.commitTransaction();
                } else realm.cancelTransaction();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean Status = jsonObject.optBoolean("Status");

                    if (Status) {
//                        Log.i("///", Status + "");

                        JSONArray jsonArray = jsonObject.optJSONArray("OtherData");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String filePth = object.optString("FilePath").replace("~", "");
                            String title = object.optString("Titel");
                            int id = object.optInt("Id");
                            boolean isDeleted = object.optBoolean("isDeleted");
                            long updatedAt = object.getLong("UpdatedAt");
                            Slider slider = new Slider(IMG_URL + filePth, title, id, isDeleted, updatedAt);
                            Log.i("///", i + "");
                            list.add(slider);
                        }

                        for (Slider b : list) {
                            // Persist your data easily
                            realm.beginTransaction();
//                            Person person = realm.createObject(Person.class); // Create managed objects directly

                            realm.copyToRealm(b);
                            realm.commitTransaction();
                            Log.i("///", "///" + "" +/* b.getImages()+b.getTitels()*/list.get(0).getTitels());

                        }
                    } else {
                        realm.cancelTransaction();
                        Toast.makeText(getApplicationContext(), jsonObject.optString("ResultText"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);
    }

    public void getTypeList() {
//        Log.i("///", "///" + "");
//        final Realm realm = Realm.getDefaultInstance();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, STATIC_URL + "getTypeList"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("///", "///" + "" + response);
                realm.beginTransaction();

                RealmResults<TypeList> result = realm.where(TypeList.class).findAll();
                if (!(result.isEmpty())) {
                    result.deleteAllFromRealm();
                    realm.commitTransaction();
                } else realm.cancelTransaction();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean Status = jsonObject.optBoolean("Status");

                    if (Status) {
//                        Log.i("///", Status + "");

                        JSONArray jsonArray = jsonObject.optJSONArray("OtherData");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            int IdType = object.optInt("IdType");
                            String Name = object.optString("Name");
                            int id = object.optInt("Id");
                            boolean isDeleted = object.optBoolean("isDeleted");
                            long updatedAt = object.getLong("UpdatedAt");
                            TypeList type = new TypeList(id, IdType, Name, isDeleted, updatedAt);
//                            Log.i("///", i + "");
                            typeList.add(type);
                        }

                        for (TypeList b : typeList) {
                            // Persist your data easily
                            realm.beginTransaction();
                            realm.copyToRealm(b);
                            realm.commitTransaction();
//                            Log.i("///", "///" + "" +/* b.getImages()+b.getTitels()*/typeList.get(0).getName());

                        }
                    } else {
                        realm.cancelTransaction();
                        Toast.makeText(getApplicationContext(), jsonObject.optString("ResultText"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error Response", Toast.LENGTH_LONG).show();

            }
        });
        requestQueue.add(request);
    }

    public void getMealsList() {
        Log.i("///", "///" + "");
//        final Realm realm = Realm.getDefaultInstance();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, STATIC_URL + "getMealsList"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("///", "///" + "" + response);
                realm.beginTransaction();
                RealmResults<Meals> result = realm.where(Meals.class).findAll();
                if (!(result.isEmpty())) {
                    result.deleteAllFromRealm();
                    realm.commitTransaction();
                } else realm.cancelTransaction();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
//                        Log.i("///", Status + "");
                        JSONArray jsonArray = jsonObject.optJSONArray("OtherData");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            int IdType = object.optInt("IdTypeList");
                            String Name = object.optString("Name");
                            String description = object.optString("Description");
                            int id = object.optInt("Id");
                            double price = object.optDouble("Price");
                            String filePth = object.optString("FilePath").replace("~", "");
                            boolean isDeleted = object.optBoolean("isDeleted");
                            boolean showInHomePage = object.optBoolean("ShowInHomePage");
                            long updatedAt = object.getLong("UpdatedAt");
                            Meals typeMeals = new Meals(id, Name, IdType, price, description, IMG_URL + filePth,
                                    isDeleted, showInHomePage, updatedAt);
//                            Log.i("///", i + "");
                            meals.add(typeMeals);
                        }
                        for (Meals b : meals) {
                            // Persist your data easily
                            realm.beginTransaction();
                            realm.copyToRealm(b);
                            realm.commitTransaction();
//                            Log.i("///", "///" + "" +/* b.getImages()+b.getTitels()*/meals.get(0).getName());
                        }
                    } else {
                        realm.cancelTransaction();
                        Toast.makeText(getApplicationContext(), jsonObject.optString("ResultText"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        Log.i("///", "///" + "");
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, STATIC_URL + "getMarketList"
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("///", "///" + "" + response);
                realm.beginTransaction();
                RealmResults<Market> result = realm.where(Market.class).findAll();
                if (!(result.isEmpty())) {
                    result.deleteAllFromRealm();
                    realm.commitTransaction();
                } else realm.cancelTransaction();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        JSONArray jsonArray = jsonObject.optJSONArray("OtherData");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            int IdTypeList = object.optInt("IdTypeList");
                            int type = object.optInt("type");
                            String Name = object.optString("Name");
                            String description = object.optString("Description");
                            int id = object.optInt("Id");
                            double price = object.optDouble("Price");
                            String filePth = object.optString("FilePath").replace("~", "");
                            boolean isDeleted = object.optBoolean("isDeleted");
                            long updatedAt = object.getLong("UpdatedAt");
                            Market typeMarket = new Market(id, type, IdTypeList, IMG_URL + filePth,Name,  description,price,
                                    isDeleted, updatedAt);
                            market.add(typeMarket);
                        }
                        for (Market b : market) {
                            // Persist your data easily
                            realm.beginTransaction();
                            realm.copyToRealm(b);
                            realm.commitTransaction();
                        }
                    } else {
                        realm.cancelTransaction();
                        Toast.makeText(getApplicationContext(), jsonObject.optString("ResultText"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
