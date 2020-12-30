package com.manoranjan.apnadriver.model;

public class AssignmentModel {
    private String id;

    private String user_id;

    private String title;

    private String category;

    private String subject;

    private String description;

    private String file;

    private String created_date;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public String getCategory(){
        return this.category;
    }
    public void setSubject(String subject){
        this.subject = subject;
    }
    public String getSubject(){
        return this.subject;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setFile(String file){
        this.file = file;
    }
    public String getFile(){
        return this.file;
    }
    public void setCreated_date(String created_date){
        this.created_date = created_date;
    }
    public String getCreated_date(){
        return this.created_date;
    }
}

