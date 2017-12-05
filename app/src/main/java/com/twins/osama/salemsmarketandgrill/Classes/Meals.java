package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 8/28/2017.
 */
@RealmClass
public class Meals extends RealmObject {
    @PrimaryKey
    private int Id;
    private String Name;
    private int IdTypeList;
    private double Price;
    private String Description;
    private String FilePath;
    private boolean isDeleted;
    private boolean ShowInHomePage;
    private long UpdatedAt;
    private RealmList<MealsAdditionsAPI> ListAdditions;
    public Meals() {

    }

    public Meals(int id, String name, int idTypeList, double price, String description, String filePath, boolean isDeleted,
                 boolean showInHomePage, long updatedAt, RealmList<MealsAdditionsAPI> listAdditions) {
        Id = id;
        Name = name;
        IdTypeList = idTypeList;
        Price = price;
        Description = description;
        FilePath = filePath;
        this.isDeleted = isDeleted;
        ShowInHomePage = showInHomePage;
        UpdatedAt = updatedAt;
        ListAdditions = listAdditions;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public RealmList<MealsAdditionsAPI> getListAdditions() {
        return ListAdditions;
    }

    public void setListAdditions(RealmList<MealsAdditionsAPI> listAdditions) {
        ListAdditions = listAdditions;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getIdTypeList() {
        return IdTypeList;
    }

    public void setIdTypeList(int idTypeList) {
        IdTypeList = idTypeList;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

//    public String getDescription() {
//        return Description;
//    }
//
//    public void setDescription(String description) {
//        Description = description;
//    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isShowInHomePage() {
        return ShowInHomePage;
    }

    public void setShowInHomePage(boolean showInHomePage) {
        ShowInHomePage = showInHomePage;
    }

    public long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        UpdatedAt = updatedAt;
    }
}
