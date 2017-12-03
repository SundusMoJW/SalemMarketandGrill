package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 9/6/2017.
 */
@RealmClass
public class Market extends RealmObject {
    @PrimaryKey
    public int Id;
    public int type; // النوع || 1=بقالة || 2=لحوم ،،،، Grocery|Meat

    // ثوابت 6و7 ||| 6=للبقالة للرقم 1 || 7=للحوم للرقم 2
    public int IdTypeList;
    public String FilePath;
    public String name;
    public String Description;
    public double Price;
    public boolean isDeleted;
    public long UpdatedAt;

    public Market() {
    }

    public Market(int id, int type, int idTypeList, String filePath, String name, String description,
                  double price, boolean isDeleted, long updatedAt) {
        this.Id = id;
        this.type = type;
        this.IdTypeList = idTypeList;
        this.FilePath = filePath;
        this.name = name;
        this.Description = description;
        this.Price = price;
        this.isDeleted = isDeleted;
        this.UpdatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIdTypeList() {
        return IdTypeList;
    }

    public void setIdTypeList(int idTypeList) {
        IdTypeList = idTypeList;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        UpdatedAt = updatedAt;
    }
}
