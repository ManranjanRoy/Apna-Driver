package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.SerializedName;

public class BookingCountResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("count")
    private int count;

   /* @SerializedName("result")
    private List<Country> result;

    public List<Country> getResult() {
        return result;
    }*/

    public String getStatus() {
        return status;
    }

    public int getCount() {
        return count;
    }
}
