package com.manoranjan.apnadriver.Sdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherReponse {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("status")
    @Expose
    private Boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
