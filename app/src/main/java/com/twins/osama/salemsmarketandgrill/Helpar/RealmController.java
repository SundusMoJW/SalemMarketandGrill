package com.twins.osama.salemsmarketandgrill.Helpar;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;

import org.json.JSONArray;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Osama on 8/26/2017.
 */

public class RealmController {


    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    public void putMealsList(JSONArray jsonArray) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(Meals.class, jsonArray);
        realm.commitTransaction();
    }

    public void putMarketList(JSONArray marketList) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(Market.class, marketList);
        realm.commitTransaction();
    }

    public void putTypeList(JSONArray typeList) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(TypeList.class, typeList);
        realm.commitTransaction();
    }

    public void putSliderImage(JSONArray sliderImage) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(Slider.class, sliderImage);
        realm.commitTransaction();
    }
    public RealmResults<CartItem> getCartItems() {
        return realm.where(CartItem.class).findAll();
    }
    public CartItem cheackCartItem(int id,int idTypeList) {
        return realm.where(CartItem.class).equalTo("Id",id).equalTo("IdTypeList",idTypeList).findFirst();
    }
}
