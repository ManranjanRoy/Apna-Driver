package com.manoranjan.apnadriver.Response;

import com.manoranjan.apnadriver.model.BookingListModel;

import java.util.List;

public class BookingListResponse {
    private String status;
    private List<BookingListModel> data;
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setData(List<BookingListModel> data){
        this.data = data;
    }
    public List<BookingListModel> getData(){
        return this.data;
    }
}
