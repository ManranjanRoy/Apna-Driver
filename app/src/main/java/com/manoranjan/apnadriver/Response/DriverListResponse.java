package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manoranjan.apnadriver.model.DriverListModel;

public class DriverListResponse {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("list")
    @Expose
    private java.util.List<DriverListModel> list = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public java.util.List<DriverListModel> getList() {
        return list;
    }

    public void setList(java.util.List<DriverListModel> list) {
        this.list = list;
    }

}