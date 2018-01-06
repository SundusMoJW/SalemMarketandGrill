package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 12/3/2017.
 */
@RealmClass
public class MarketAdditionsAPI extends RealmObject {
    @PrimaryKey
    private int Id ;
    private int IdMarket ;
    private int IdAdditions;
    private String AdditionsText;
    private boolean select =true;
    public MarketAdditionsAPI() {
    }

    public MarketAdditionsAPI(int id, int idMarket, int idAdditions, String additionsText,boolean select) {
        Id = id;
        IdMarket = idMarket;
        IdAdditions = idAdditions;
        AdditionsText = additionsText;
        this.select=select;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdMarket() {
        return IdMarket;
    }

    public void setIdMarket(int idMarket) {
        IdMarket = idMarket;
    }

    public int getIdAdditions() {
        return IdAdditions;
    }

    public void setIdAdditions(int idAdditions) {
        IdAdditions = idAdditions;
    }

    public String getAdditionsText() {
        return AdditionsText;
    }

    public void setAdditionsText(String additionsText) {
        AdditionsText = additionsText;
    }
}
