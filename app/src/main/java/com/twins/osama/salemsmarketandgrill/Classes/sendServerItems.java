package com.twins.osama.salemsmarketandgrill.Classes;

import java.util.List;

/**
 * Created by Osama on 12/3/2017.
 */

public class sendServerItems {
    private int idMarket;
    private int idMeals;
    private int count;
    private List<Integer> listAdditions;

    public sendServerItems(int idMarket, int idMeals, int count,
                           List<Integer> listAdditions) {
        this.idMarket = idMarket;
        this.idMeals = idMeals;
        this.count = count;
        this.listAdditions = listAdditions;
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

    public List<Integer> getListAdditions() {
        return listAdditions;
    }

    public void setListAdditions(List<Integer> listAdditions) {
        this.listAdditions = listAdditions;
    }
}
