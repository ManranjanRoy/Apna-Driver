package com.manoranjan.apnadriver.Response;


import com.manoranjan.apnadriver.model.SuburbPostCodeModel;

import java.util.List;

public class SuburbPostcodeResponse {
    private String status;
    private List<SuburbPostCodeModel> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SuburbPostCodeModel> getList() {
        return list;
    }

    public void setList(List<SuburbPostCodeModel> list) {
        this.list = list;
    }
}
