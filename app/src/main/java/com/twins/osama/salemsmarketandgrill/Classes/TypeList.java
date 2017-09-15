package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 8/27/2017.
 */
@RealmClass
public class TypeList extends RealmObject {
    public int Id;
    public int IdType;
    public String name;
    public boolean isDeleted;
    public long UpdatedAt;

    public TypeList() {
    }
    public TypeList(int id, int idType, String name, boolean isDeleted, long updatedAt) {
        Id = id;
        IdType = idType;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
