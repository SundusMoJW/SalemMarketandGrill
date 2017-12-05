package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 9/6/2017.
 */
@RealmClass
public class Market extends RealmObject {
    @PrimaryKey
    private int Id;
    private int type; // النوع || 1=بقالة || 2=لحوم ،،،، Grocery|Meat
    // ثوابت 6و7 ||| 6=للبقالة للرقم 1 || 7=للحوم للرقم 2
    private int IdTypeList;
    private String FilePath;
    private String Name;
    private String Description;
    private double Price;
    private boolean isDeleted;
    private long UpdatedAt;
    private RealmList<MarketAdditionsAPI> ListAdditions;

    public Market() {
    }

    public Market(int id, int type, int idTypeList, String filePath, String name, String description, double price,
                  boolean isDeleted, long updatedAt, RealmList<MarketAdditionsAPI> listAdditions) {
        Id = id;
        this.type = type;
        IdTypeList = idTypeList;
        FilePath = filePath;
        Name = name;
        Description = description;
        Price = price;
        this.isDeleted = isDeleted;
        UpdatedAt = updatedAt;
        ListAdditions = listAdditions;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public RealmList<MarketAdditionsAPI> getListAdditions() {
        return ListAdditions;
    }

    public void setListAdditions(RealmList<MarketAdditionsAPI> listAdditions) {
        ListAdditions = listAdditions;
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
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

//    public String getDescription() {
//        return Description;
//    }
//
//    public void setDescription(String description) {
//        Description = description;
//    }

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
