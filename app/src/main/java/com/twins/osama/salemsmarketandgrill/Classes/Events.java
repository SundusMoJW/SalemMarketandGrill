package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 12/12/2017.
 */
@RealmClass
public class Events extends RealmObject {
    @PrimaryKey
    private int Id ;
    private String Title ;// عنوان الحدث
    private int TypeEvent ; // نوع الحدث :: 1=حدث داخل المطعم || 2=فعليات خارج المطعم
    private String FilePath; // صورة الحدث
    private String Description ; // الوصف
    private Long FromDate; // من تاريخ
    private Long ToDate ; // الى تاريخ
    private boolean isDeleted ; // هل هو محذوف ؟
    private Long CreatedAt ; // وقت الاضافة
    private int IdTypeList ; // تصنيف الحدث
    private String IdTypeListText ; // تصنيف الحدث
    private int MaxPeople ; // اكبر عدد استيعاب من الاشخاص
    private Long UpdatedAt ; // اخر تحديث

    private RealmList<EventsPhotoAPI> ListPhoto ; // صور

    public Events() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getTypeEvent() {
        return TypeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        TypeEvent = typeEvent;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Long getFromDate() {
        return FromDate;
    }

    public void setFromDate(Long fromDate) {
        FromDate = fromDate;
    }

    public Long getToDate() {
        return ToDate;
    }

    public void setToDate(Long toDate) {
        ToDate = toDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Long getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Long createdAt) {
        CreatedAt = createdAt;
    }

    public int getIdTypeList() {
        return IdTypeList;
    }

    public void setIdTypeList(int idTypeList) {
        IdTypeList = idTypeList;
    }

    public String getIdTypeListText() {
        return IdTypeListText;
    }

    public void setIdTypeListText(String idTypeListText) {
        IdTypeListText = idTypeListText;
    }

    public int getMaxPeople() {
        return MaxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        MaxPeople = maxPeople;
    }

    public Long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        UpdatedAt = updatedAt;
    }

    public RealmList<EventsPhotoAPI> getListPhoto() {
        return ListPhoto;
    }

    public void setListPhoto(RealmList<EventsPhotoAPI> listPhoto) {
        ListPhoto = listPhoto;
    }
}
