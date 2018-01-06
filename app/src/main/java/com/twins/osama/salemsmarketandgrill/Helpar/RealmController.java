package com.twins.osama.salemsmarketandgrill.Helpar;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Classes.Events;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.News;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;

import org.json.JSONArray;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

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

    public void putNewsList(JSONArray newsList) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(News.class, newsList);
        realm.commitTransaction();
    }

    public void putEventsList(JSONArray eventsList) {
        realm.beginTransaction();
        realm.createOrUpdateAllFromJson(Events.class, eventsList);
        realm.commitTransaction();
    }

    public RealmResults<CartItem> getCartItems() {
        return realm.where(CartItem.class).findAll();
    }

    public CartItem cheackCartItem(int id, int idTypeList) {
        return realm.where(CartItem.class).equalTo("Id", id).equalTo("IdTypeList", idTypeList).findFirst();
    }

    public void putInCartItem(CartItem cartItem) {
        realm.beginTransaction();
        realm.copyToRealm(cartItem);
        realm.commitTransaction();
    }

    public long lastUpdateMarket() {
        RealmResults<Market> allTransactions = realm.where(Market.class).equalTo("isDeleted", false).findAllSorted("UpdatedAt");
        long lastInsertedId = 0;
        if (allTransactions != null && !(allTransactions.isEmpty()) && allTransactions.size() != 0) {
            lastInsertedId = allTransactions.last().getUpdatedAt();
        }
        return lastInsertedId;
    }

    public long lastUpdateMeals() {
        RealmResults<Meals> allTransactions = realm.where(Meals.class).equalTo("isDeleted", false).findAllSorted("UpdatedAt");
        long lastInsertedId = 0;
        if (allTransactions != null && !(allTransactions.isEmpty()) && allTransactions.size() != 0) {
            lastInsertedId = allTransactions.last().getUpdatedAt();
        }
        return lastInsertedId;
    }

    public long lastUpdateTypeList() {
        RealmResults<TypeList> allTransactions = realm.where(TypeList.class).equalTo("isDeleted", false).findAllSorted("UpdatedAt");
        long lastInsertedId = 0;
        if (allTransactions != null && !(allTransactions.isEmpty()) && allTransactions.size() != 0) {
            lastInsertedId = allTransactions.last().getUpdatedAt();
        }
        return lastInsertedId;
    }

    public long lastUpdateSlider() {
        RealmResults<Slider> allTransactions = realm.where(Slider.class).equalTo("isDeleted", false).findAllSorted("UpdatedAt");
        long lastInsertedId = 0;
        if (allTransactions != null && !(allTransactions.isEmpty()) && allTransactions.size() != 0) {
            lastInsertedId = allTransactions.last().getUpdatedAt();
        }
        return lastInsertedId;
    }

    public long lastUpdateEvents() {
        RealmResults<Events> allTransactions = realm.where(Events.class).equalTo("isDeleted", false).findAllSorted("UpdatedAt");
        long lastInsertedId = 0;
        if (allTransactions != null && !(allTransactions.isEmpty()) && allTransactions.size() != 0) {
            lastInsertedId = allTransactions.last().getUpdatedAt();
        }
        return lastInsertedId;
    }

    public long lastUpdateNews() {
        RealmResults<News> allTransactions = realm.where(News.class).equalTo("isDeleted", false).findAllSorted("UpdatedAt");
        long lastInsertedId = 0;
        if (allTransactions != null && !(allTransactions.isEmpty()) && allTransactions.size() != 0) {
            lastInsertedId = allTransactions.last().getUpdatedAt();
        }
        return lastInsertedId;
    }

    public ArrayList<News> getNewsList() {

        RealmResults<News> newsRealmResults = realm.where(News.class)
                .equalTo("isDeleted", false)
                .findAll()
                .sort("CreatedAt", Sort.DESCENDING);
        ArrayList<News> newsArrayList = (ArrayList<News>) realm.copyFromRealm(newsRealmResults);
        return newsArrayList;
    }

    public ArrayList<Events> getOutEventsList() {

        RealmResults<Events> eventsRealmResults = realm.where(Events.class)
                .equalTo("isDeleted", false)
                .equalTo("TypeEvent",2)
                .findAll()
                .sort("CreatedAt", Sort.DESCENDING);
        ArrayList<Events> eventsArrayList = (ArrayList<Events>) realm.copyFromRealm(eventsRealmResults);
        return eventsArrayList;
    }
    public ArrayList<Events> getInEventsList() {

        RealmResults<Events> eventsRealmResults = realm.where(Events.class)
                .equalTo("isDeleted", false)
                .equalTo("TypeEvent",1)
                .findAll()
                .sort("CreatedAt", Sort.DESCENDING);
        ArrayList<Events> eventsArrayList = (ArrayList<Events>) realm.copyFromRealm(eventsRealmResults);
        return eventsArrayList;
    }
    public double getPriceMarket(int id, int idTypeList) {
        return realm.where(Market.class)
                .equalTo("Id", id)
                .equalTo("IdTypeList", idTypeList)
                .equalTo("isDeleted", false)
                .findFirst()
                .getPrice();
    }

    public double getPriceMeals(int id, int idTypeList) {
        return realm.where(Meals.class)
                .equalTo("Id", id)
                .equalTo("IdTypeList", idTypeList)
                .equalTo("isDeleted", false)
                .findFirst()
                .getPrice();
    }

    public double getPrice(int id, int idTypeList) {

        double price;
        int idType = realm.where(TypeList.class)
                .equalTo("Id", idTypeList)
                .equalTo("isDeleted", false)
                .findFirst()
                .getIdType();
        if (idType == 2) {
            price = realm.where(Meals.class)
                    .equalTo("Id", id)
                    .equalTo("IdTypeList", idTypeList)
                    .equalTo("isDeleted", false)
                    .findFirst()
                    .getPrice();
        } else {
            price = realm.where(Market.class)
                    .equalTo("Id", id)
                    .equalTo("IdTypeList", idTypeList)
                    .equalTo("isDeleted", false)
                    .findFirst()
                    .getPrice();
        }
        return price;
    }


    public News cheackNews(int id) {
        return realm.where(News.class).equalTo("Id", id).equalTo("isDeleted", false).findFirst();
    }
    public Events cheackEvents(int id) {
        return realm.where(Events.class).equalTo("Id", id).equalTo("isDeleted", false).findFirst();
    }
}
