package com.twins.osama.salemsmarketandgrill.Template;

/**
 * Created by Osama on 7/26/2017.
 */

public class RVGallaryTemplate {
    private   String main;
    private int images;
    private  String info;
    private int expand;


    public  RVGallaryTemplate(int images, String main , String info, int expand ){
        this.images=images;
        this.info = info;
        this.main=main;
        this.expand=expand;


    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getExpand() {
        return expand;
    }

    public void setExpand(int expand) {
        this.expand = expand;
    }
}
