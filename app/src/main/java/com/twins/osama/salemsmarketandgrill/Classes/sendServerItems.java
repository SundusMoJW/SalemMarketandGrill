package com.twins.osama.salemsmarketandgrill.Classes;

import java.util.List;

/**
 * Created by Osama on 12/3/2017.
 */

public class sendServerItems {
    private int idMarket;
    private int idMeals;
    private int count;
    private List<MarketAdditionsAPI> marketListAdditions;
    private List<MealsAdditionsAPI> mealListAdditions;

    public sendServerItems(int idMarket, int idMeals, int count, List<MarketAdditionsAPI> marketListAdditions,
                           List<MealsAdditionsAPI> mealListAdditions) {
        this.idMarket = idMarket;
        this.idMeals = idMeals;
        this.count = count;
        this.marketListAdditions = marketListAdditions;
        this.mealListAdditions = mealListAdditions;
    }

    public int getIdMarket() {
        return idMarket;
    }

    public void setIdMarket(int idMarket) {
        this.idMarket = idMarket;
    }

    public int getIdMeals() {
        return idMeals;
    }

    public void setIdMeals(int idMeals) {
        this.idMeals = idMeals;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MarketAdditionsAPI> getMarketListAdditions() {
        return marketListAdditions;
    }

    public void setMarketListAdditions(List<MarketAdditionsAPI> marketListAdditions) {
        this.marketListAdditions = marketListAdditions;
    }

    public List<MealsAdditionsAPI> getMealListAdditions() {
        return mealListAdditions;
    }

    public void setMealListAdditions(List<MealsAdditionsAPI> mealListAdditions) {
        this.mealListAdditions = mealListAdditions;
    }
}
