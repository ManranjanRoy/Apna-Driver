package com.manoranjan.apnadriver.model;

public class VideoCatagoryModel {
    private String id;

    private String name;

    private String image;

    private String status;

    private String created_date;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setImage(String image){
        this.image = image;
    }
    public String getImage(){
        return this.image;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setCreated_date(String created_date){
        this.created_date = created_date;
    }
    public String getCreated_date(){
        return this.created_date;
    }
}

