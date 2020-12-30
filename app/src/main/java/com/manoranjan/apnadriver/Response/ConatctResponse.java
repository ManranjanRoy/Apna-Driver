package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.SerializedName;

public class ConatctResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String messages;

    public String getStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }
}
