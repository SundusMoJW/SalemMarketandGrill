package com.twins.osama.salemsmarketandgrill.Classes;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 8/24/2017.
 */
@RealmClass
public class Slider extends RealmObject implements Parcelable {
    String images;
    String titels;
    @PrimaryKey
    int Id;
    boolean isDeleted;
    long UpdatedAt;

    public Slider() {
    }

    public Slider(String images, String titels, int id, boolean isDeleted, long updatedAt) {
        this.images = images;
        this.titels = titels;
        Id = id;
        this.isDeleted = isDeleted;
        UpdatedAt = updatedAt;
    }

//    public Slider(String images, String titels) {
//
//        this.images = images;
//        this.titels = titels;
//    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getTitels() {
        return titels;
    }

    public void setTitels(String titels) {
        this.titels = titels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(images);
        dest.writeString(titels);
    }
}
