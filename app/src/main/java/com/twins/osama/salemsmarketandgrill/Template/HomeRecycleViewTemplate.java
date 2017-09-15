package com.twins.osama.salemsmarketandgrill.Template;

/**
 * Created by Osama on 7/24/2017.
 */

public class HomeRecycleViewTemplate {
    public  String main;
    public int images;
    public  String title;
    public String salary;

    public  HomeRecycleViewTemplate(int images, String salary , String title, String main ){
        this.images=images;
        this.title = title;
        this.salary=salary;
        this.main=main;


    }

    public String getMain() {
        return main;
    }

    public int getImages() {
        return images;
    }

    public String getTitle() {
        return title;
    }

    public String getSalary() {
        return salary;
    }
}

