package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.SerializedName;
import com.manoranjan.apnadriver.model.HourModel;
import com.manoranjan.apnadriver.model.PricingModel;

import java.util.List;

public class PricingResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String messages;
    @SerializedName("list")
    private List<PricingModel> data;

    public List<PricingModel> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }

}
