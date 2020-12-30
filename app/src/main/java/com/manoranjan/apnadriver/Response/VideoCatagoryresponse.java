package com.manoranjan.apnadriver.Response;

import com.manoranjan.apnadriver.model.VideoCatagoryModel;

import java.util.List;

public class VideoCatagoryresponse {
    private boolean status;

    private List<VideoCatagoryModel> data;
    private  String message;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setData(List<VideoCatagoryModel> data){
        this.data = data;
    }
    public List<VideoCatagoryModel> getData(){
        return this.data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
