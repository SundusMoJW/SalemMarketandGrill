package com.twins.osama.salemsmarketandgrill.Helpar;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.STATIC_URL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URLF;

/**
 * Created by iSaleem on 11/29/17.
 */

public class VolleyRequests {

    public interface IReceiveData {
        void onDataReceived(Object o);
    }

    private IReceiveData iReceiveData;

    public IReceiveData getIReceiveData() {
        return iReceiveData;
    }

    public VolleyRequests setIReceiveData(IReceiveData iReceiveData) {
        this.iReceiveData = iReceiveData;
        return this;
    }

    public void getMealsList() {

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
                    if (iReceiveData != null) {
                        iReceiveData.onDataReceived(jsonArray);
                    }
//                    RealmController.with().getInstance().putMealsList(jsonArray);
                    Log.i("OtherData", jsonArray.toString());
                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage()+"");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getMarketList() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMarketList", null
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
                    JSONArray marketList = response.optJSONArray("OtherData");
                    if (iReceiveData != null) {
                        iReceiveData.onDataReceived(marketList);
                    }
                    Log.i("OtherData", marketList.toString());
                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage()+"");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getTypeList() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getTypeList", null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                if (Status) {
                    JSONArray jsonArray = response.optJSONArray("OtherData");
                    if (iReceiveData != null) {
                        iReceiveData.onDataReceived(jsonArray);
                    }
                    Log.i("OtherData", jsonArray.toString());
                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage()+"");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
   }

    public void getSliderImage() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLF, null
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
                    if (iReceiveData != null) {
                        iReceiveData.onDataReceived(jsonArray);
                    }
                    Log.i("OtherData", jsonArray.toString());
                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage()+"");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getMarketDetails(final int idMarket) {
        HashMap<String,String> params = new HashMap<>();
        params.put("IdMarket",idMarket+"");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMarketDetails", new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                Log.i("//response", response.toString());

//                realm.beginTransaction();
//                RealmResults<Meals> result = realm.where(Meals.class).findAll();
//                if (!(result.isEmpty())) {
//                    result.deleteAllFromRealm();
//                    realm.commitTransaction();
//                } else realm.cancelTransaction();
                if (Status) {
                    Log.d("//Status", response.optJSONObject("OtherData")+"");
                    Log.d("//Status", response.optString("OtherData")+"");
                    Log.d("//Status", response.optString("Description")+"");

                    String description = response.optJSONObject("OtherData").optString("Description");
//                    JSONObject description=marketList.getJSONObject()
                    if (iReceiveData != null) {
                        iReceiveData.onDataReceived(description);
                    }
                    Log.i("OtherData", description.toString());
                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", error.getMessage()+"");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getMealsDetails(final int idMarket) {
        Log.d("getMealsDetails",idMarket+"");
        HashMap<String,String> params = new HashMap<>();
        params.put("IdMeals", String.valueOf(idMarket));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMealsDetails", new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                Log.i("//response", response.toString());

                if (Status) {
                    Log.d("//Status", response.optJSONObject("OtherData")+"");
                    Log.d("//Status", response.optString("OtherData")+"");
                    Log.d("//Status", response.optString("Description")+"");

//                    .optString("Description")
                    String description = response.optJSONObject("OtherData").optString("Description");

                    if (iReceiveData != null) {
                        if(description!=null) {
                            iReceiveData.onDataReceived(description);
                        }else iReceiveData.onDataReceived("");
                    }
                    Log.d("//description", description.toString());
                    Log.d("//responseDescription", response.toString());
                } else {
                    Log.d("//responseDescription", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("//error", error.getMessage().toString()+"");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }
}
