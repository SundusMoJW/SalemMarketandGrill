package com.twins.osama.salemsmarketandgrill.Classes;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Osama on 12/12/2017.
 */
@RealmClass
public class News extends RealmObject {
    @PrimaryKey
    private int Id ;
    private String FilePath ;// الصورة
    private int IdTypeList ;// رقم التصنيف
    private String IdTypeListText ; // اسم التصنيف
    private String Title ; // عنوان الخبر
    private String Body ; // نص الخبر html
    private boolean isDeleted ; // هل هو محذوف ؟
    private Long CreatedAt ;// تاريخ الخبر
    private Long  UpdatedAt ; // اخر تحديث

    public News() {
    }

    public News(int id, String filePath, int idTypeList, String idTypeListText, String title, String body, boolean isDeleted, Long createdAt, Long updatedAt) {
        Id = id;
        FilePath = filePath;
        IdTypeList = idTypeList;
        IdTypeListText = idTypeListText;
        Title = title;
        Body = body;
        this.isDeleted = isDeleted;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
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

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
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

    public Long getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        UpdatedAt = updatedAt;
    }
}
