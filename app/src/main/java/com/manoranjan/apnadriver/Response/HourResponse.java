package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.SerializedName;
import com.manoranjan.apnadriver.model.HourModel;
import com.manoranjan.apnadriver.model.Profile;

import java.util.List;

public class HourResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String messages;
    @SerializedName("list")
    private List<HourModel> data;

    public List<HourModel> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }

}
