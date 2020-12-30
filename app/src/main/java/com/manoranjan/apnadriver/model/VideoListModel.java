package com.manoranjan.apnadriver.model;

public class VideoListModel {
    private String id;

    private String cat_id;

    private String name;

    private String image;

    private String video;

    private String status;

    private String  created_date;

    private String  updated_date;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setCat_id(String cat_id){
        this.cat_id = cat_id;
    }
    public String getCat_id(){
        return this.cat_id;
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
    public void setVideo(String video){
        this.video = video;
    }
    public String getVideo(){
        return this.video;
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
    public void setUpdated_date(String updated_date){
        this.updated_date = updated_date;
    }
    public String getUpdated_date(){
        return this.updated_date;
    }
}
