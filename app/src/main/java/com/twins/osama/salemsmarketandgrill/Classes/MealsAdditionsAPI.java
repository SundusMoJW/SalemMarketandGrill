package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 12/3/2017.
 */
@RealmClass
public class MealsAdditionsAPI extends RealmObject {
    @PrimaryKey
    private int Id;
    private int IdMeals;
    private int IdAdditions;
    private String AdditionsText;

    public MealsAdditionsAPI() {
    }

    public MealsAdditionsAPI(int id, int idMeals, int idAdditions, String additionsText) {
        Id = id;
        IdMeals = idMeals;
        IdAdditions = idAdditions;
        AdditionsText = additionsText;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdMeals() {
        return IdMeals;
    }

    public void setIdMeals(int idMeals) {
        IdMeals = idMeals;
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
