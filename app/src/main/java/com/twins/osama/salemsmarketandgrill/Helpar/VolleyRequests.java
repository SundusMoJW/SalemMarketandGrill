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

    public void getMealsList(final long lastUpdate) {
        HashMap<String,String> params = new HashMap<>();
        params.put("lastUpdate", String.valueOf(lastUpdate));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMealsList", new JSONObject(params)
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
                Log.d("error", "getMealsList");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getMarketList(final long lastUpdate) {
        HashMap<String,String> params = new HashMap<>();
        params.put("lastUpdate", String.valueOf(lastUpdate));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMarketList",new JSONObject(params)
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
//                    Log.i("OtherData", marketList.toString());
//                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "getMarketList");
            }
        });
        UIApplication.getAnInstance().

                addToRequestQueue(request);

    }

    public void getTypeList(final long lastUpdate) {
        HashMap<String,String> params = new HashMap<>();
        params.put("lastUpdate", String.valueOf(lastUpdate));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getTypeList", new JSONObject(params)
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
                Log.d("//error", "getTypeList");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getSliderImage(final long lastUpdate) {
        HashMap<String,String> params = new HashMap<>();
        params.put("lastUpdate", String.valueOf(lastUpdate));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLF, new JSONObject(params)
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
                Log.d("//error", "getSliderImage");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getMarketDetails(final int idMarket) {
        HashMap<String, String> params = new HashMap<>();
        params.put("IdMarket", idMarket + "");
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
                    Log.d("//Status", response.optJSONObject("OtherData") + "");
                    Log.d("//Status1", response.optString("OtherData") + "");
                    Log.d("//Status2", response.optString("Description") + "");

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
                Log.d("//error", "getMarketDetails");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getMealsDetails(final int idMarket) {
        Log.d("getMealsDetails", idMarket + "");
        HashMap<String, String> params = new HashMap<>();
        params.put("IdMeals", String.valueOf(idMarket));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getMealsDetails", new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                Log.i("//response", response.toString());

                if (Status) {
                    Log.d("//Status1", response.optJSONObject("OtherData") + "");
                    Log.d("//Status2", response.optString("OtherData") + "");
                    Log.d("//Status3", response.optString("Description") + "");

//                    .optString("Description")
                    String description = response.optJSONObject("OtherData").optString("Description");

                    if (iReceiveData != null) {
                        if (description != null) {
                            iReceiveData.onDataReceived(description);
                        } else iReceiveData.onDataReceived("");
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
                Log.d("//error", "getMealsDetails");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getNewstList(final long lastUpdate) {
        HashMap<String,String> params = new HashMap<>();
        params.put("UpdatedAt", String.valueOf(lastUpdate));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getNewsList",new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                Log.d("***StatusNewstList", Status+"");

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
//                    Log.i("OtherData", marketList.toString());
//                    Log.i("response", response.toString());
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","getNewstList");
            }
        });
        UIApplication.getAnInstance().

                addToRequestQueue(request);

    }

    public void getEventstList(final long lastUpdate) {
        HashMap<String,String> params = new HashMap<>();
        params.put("UpdatedAt", String.valueOf(lastUpdate));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getEventsList",new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
//                Log.d("***StatusEventstList", Status+"");

                if (Status) {
                    JSONArray marketList = response.optJSONArray("OtherData");
                    if (iReceiveData != null) {
                        iReceiveData.onDataReceived(marketList);
                        Log.d("***StatusEventstList", marketList.toString());

                    }
                } else {
                    Log.d("response", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error","getEventstList");
            }
        });
        UIApplication.getAnInstance().
                addToRequestQueue(request);
    }

    public void getNewsDetails(final int id) {
        Log.d("getNewsDetails", id + "");
        HashMap<String, String> params = new HashMap<>();
        params.put("Id", String.valueOf(id));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getNewsDetails", new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
//                Log.i("//response", response.toString());

                if (Status) {
//                    Log.d("//Status1", response.optJSONObject("OtherData") + "");
//                    Log.d("//Status2", response.optString("OtherData") + "");
//                    Log.d("//Status3", response.optString("Description") + "");

//                    .optString("Description")
                    String description = response.optJSONObject("OtherData").optString("Body");

                    if (iReceiveData != null) {
                        if (description != null) {
                            iReceiveData.onDataReceived(description);
                        } else iReceiveData.onDataReceived("");
                    }
//                    Log.d("//descriptionNews", response.optJSONObject("OtherData").optString("Body").toString());
//                    Log.d("//responseDescription", response.toString());
                } else {
                    Log.d("//responseDescription", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "getNewsDetails");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }

    public void getEventsDetails(final int id) {
        Log.d("getEventsDetails", id + "");
        HashMap<String, String> params = new HashMap<>();
        params.put("Id", String.valueOf(id));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, STATIC_URL + "getEventsDetails", new JSONObject(params)
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                boolean Status = response.optBoolean("Status");
                Log.i("//response", response.toString());

                if (Status) {
//                    Log.d("//Status1", response.optJSONObject("OtherData") + "");
//                    Log.d("//Status2", response.optString("OtherData") + "");
//                    Log.d("//Status3", response.optString("Description") + "");

//                    .optString("Description")
                    String description = response.optJSONObject("OtherData").optString("Description");

                    if (iReceiveData != null) {
                        if (description != null) {
                            iReceiveData.onDataReceived(description);
                        } else iReceiveData.onDataReceived("");
                    }
//                    Log.d("//description", description.toString());
//                    Log.d("//responseDescription", response.toString());
                } else {
                    Log.d("//responseDescription", "Not Allo");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "getEventsDetails");
            }
        });
        UIApplication.getAnInstance().addToRequestQueue(request);
    }
}
