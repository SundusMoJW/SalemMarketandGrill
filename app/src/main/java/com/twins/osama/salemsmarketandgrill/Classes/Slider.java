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
    String FilePath;
    String Link;
    String Description;
    String Titel;
    @PrimaryKey
    int Id;
    boolean isDeleted;
    long UpdatedAt;

    public Slider() {
    }

    public Slider(String FilePath, String Titel, int id, boolean isDeleted, long updatedAt, String Description,String Link) {
        this.FilePath = FilePath;
        this.Titel = Titel;
        this.Id = id;
        this.isDeleted = isDeleted;
        this.UpdatedAt = updatedAt;
        this.Description = Description;
        this.Link = Link;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
//    public Slider(String FilePath, String Titel) {
//
//        this.FilePath = FilePath;
//        this.Titel = Titel;
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

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public String getTitel() {
        return Titel;
    }

    public void setTitel(String Titel) {
        this.Titel = Titel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(FilePath);
        dest.writeString(Titel);
    }
}
