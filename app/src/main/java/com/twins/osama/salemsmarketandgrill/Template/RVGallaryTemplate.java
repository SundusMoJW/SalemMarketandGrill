package com.twins.osama.salemsmarketandgrill.Template;

/**
 * Created by Osama on 7/26/2017.
 */

public class RVGallaryTemplate {
    public  String main;
    public int images;
    public  String info;
    int expand;


    public  RVGallaryTemplate(int images, String main , String info,int expand ){
        this.images=images;
        this.info = info;
        this.main=main;
        this.expand=expand;


    }
}
