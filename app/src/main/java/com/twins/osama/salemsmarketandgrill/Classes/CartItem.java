package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 12/3/2017.
 */
@RealmClass
public class CartItem extends RealmObject {
    private String Name;
    private int Id;
    private String FilePath;
    private int IdTypeList;
    private int count;
    private double Price;
    private boolean select =false;

    private RealmList<MarketAdditionsAPI> marketListAdditions;
    private RealmList<MealsAdditionsAPI> mealListAdditions;

    public CartItem(String name, int id, String filePath, int idTypeList, int count, double price,boolean select,
                    RealmList<MarketAdditionsAPI> marketListAdditions, RealmList<MealsAdditionsAPI> mealListAdditions) {
        Name = name;
        Id = id;
        FilePath = filePath;
        IdTypeList = idTypeList;
        this.count = count;
        Price = price;
        this.marketListAdditions = marketListAdditions;
        this.mealListAdditions = mealListAdditions;
        this.select=select;
    }

    public CartItem() {}

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public int getIdTypeList() {
        return IdTypeList;
    }

    public void setIdTypeList(int idTypeList) {
        IdTypeList = idTypeList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public RealmList<MarketAdditionsAPI> getMarketListAdditions() {
        return marketListAdditions;
    }

    public void setMarketListAdditions(RealmList<MarketAdditionsAPI> marketListAdditions) {
        this.marketListAdditions = marketListAdditions;
    }

    public RealmList<MealsAdditionsAPI> getMealListAdditions() {
        return mealListAdditions;
    }

    public void setMealListAdditions(RealmList<MealsAdditionsAPI> mealListAdditions) {
        this.mealListAdditions = mealListAdditions;
    }
}
