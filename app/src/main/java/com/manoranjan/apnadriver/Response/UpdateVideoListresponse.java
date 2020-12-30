package com.manoranjan.apnadriver.Response;

public class UpdateVideoListresponse {
    private boolean status;


    private  String message;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
