package com.manoranjan.apnadriver.Response;


import com.manoranjan.apnadriver.model.AllHistoryModel;

import java.util.List;

public class AllHistoryResponse {
    private boolean status;

    private List<AllHistoryModel> data;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setData(List<AllHistoryModel> data){
        this.data = data;
    }
    public List<AllHistoryModel> getData(){
        return this.data;
    }
}
