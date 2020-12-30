package com.manoranjan.apnadriver.model;

public class SuburbPostCodeModel {
    String location_id,name,code;

    public SuburbPostCodeModel() {
    }

    public SuburbPostCodeModel(String location_id, String name) {
        this.location_id = location_id;
        this.name = name;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
