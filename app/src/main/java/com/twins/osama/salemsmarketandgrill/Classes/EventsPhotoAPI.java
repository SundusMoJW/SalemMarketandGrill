package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 12/12/2017.
 */
@RealmClass
public class EventsPhotoAPI extends RealmObject {
    @PrimaryKey
    private int Id ;
    private int IdEvent ;
    private String FilePath ;

    public EventsPhotoAPI() {
    }

    public EventsPhotoAPI(int id, int idEvent, String filePath) {
        Id = id;
        IdEvent = idEvent;
        FilePath = filePath;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int idEvent) {
        IdEvent = idEvent;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }
}
