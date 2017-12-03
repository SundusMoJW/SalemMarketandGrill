package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 8/27/2017.
 */
@RealmClass
public class TypeList extends RealmObject {
    @PrimaryKey
    private int Id;
    private int IdType;
    private String Name;
    private boolean isDeleted;
    private long UpdatedAt;
    private String FilePath;

    public TypeList() {
    }

    public TypeList(int id, int idType, String Name, boolean isDeleted, long updatedAt) {
        Id = id;
        IdType = idType;
        this.Name = Name;
        this.isDeleted = isDeleted;
        UpdatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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
