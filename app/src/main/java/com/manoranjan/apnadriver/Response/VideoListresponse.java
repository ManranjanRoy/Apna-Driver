package com.manoranjan.apnadriver.Response;

import com.manoranjan.apnadriver.model.VideoListModel;

import java.util.List;

public class VideoListresponse {
    private boolean status;

    private List<VideoListModel> data;
    private  String message;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setData(List<VideoListModel> data){
        this.data = data;
    }
    public List<VideoListModel> getData(){
        return this.data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
