package com.manoranjan.apnadriver.Response;

import com.manoranjan.apnadriver.model.AssignmentModel;

import java.util.List;

public class AssignmentListresponse {
    private boolean status;

    private List<AssignmentModel> data;
    private  String message;

    public void setStatus(boolean status){
        this.status = status;
    }
    public boolean getStatus(){
        return this.status;
    }
    public void setData(List<AssignmentModel> data){
        this.data = data;
    }
    public List<AssignmentModel> getData(){
        return this.data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
