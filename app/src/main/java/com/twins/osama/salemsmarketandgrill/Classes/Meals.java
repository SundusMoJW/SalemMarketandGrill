package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 8/28/2017.
 */
@RealmClass
public class Meals extends RealmObject {
    @PrimaryKey
    public int Id;
    public String name;
    public int IdTypeList;
    public double Price;
    public String Description;
    public String FilePath;
    public boolean isDeleted;
    public boolean ShowInHomePage;
    public long UpdatedAt;

    public Meals(){

    }
    public Meals(int id, String name, int idTypeList, double price, String description, String filePath,
                 boolean isDeleted, boolean showInHomePage, long updatedAt) {
        this.Id = id;
        this.name = name;
        this.IdTypeList = idTypeList;
        this.Price = price;
        this.Description = description;
        this.FilePath = filePath;
        this.isDeleted = isDeleted;
        this.ShowInHomePage = showInHomePage;
        this.UpdatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

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
