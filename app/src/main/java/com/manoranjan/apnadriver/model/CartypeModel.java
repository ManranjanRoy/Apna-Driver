package com.manoranjan.apnadriver.model;

public class CartypeModel {
    String id,name,created_date;

    public CartypeModel() {
    }

    public CartypeModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
