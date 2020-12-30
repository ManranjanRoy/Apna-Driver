package com.manoranjan.apnadriver.Response;


import com.manoranjan.apnadriver.model.CartypeModel;
import com.manoranjan.apnadriver.model.SuburbPostCodeModel;

import java.util.List;

public class CarTypeResponse {
    private String status;
    private List<CartypeModel> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CartypeModel> getList() {
        return list;
    }

    public void setList(List<CartypeModel> list) {
        this.list = list;
    }
}
