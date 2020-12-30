package com.manoranjan.apnadriver.Response;

import com.google.gson.annotations.SerializedName;

public class SignupResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("msg")
    private String messages;
    @SerializedName("token_code")
    private String token_code;

   /* @SerializedName("result")
    private List<Country> result;

    public List<Country> getResult() {
        return result;
    }*/

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
