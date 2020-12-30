package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.SerializedName;
import com.manoranjan.apnadriver.model.Profile;

import java.util.List;

public class LoginResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String messages;
    @SerializedName("access_token")
    private String token_code;

    @SerializedName("data")
    private List<Profile> data;

    public List<Profile> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessages() {
        return messages;
    }

    public String getToken_code() {
        return token_code;
    }
}
