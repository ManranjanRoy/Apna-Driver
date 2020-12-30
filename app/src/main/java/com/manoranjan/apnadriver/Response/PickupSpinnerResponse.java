package com.manoranjan.apnadriver.Response;

import com.manoranjan.apnadriver.model.PickupSpinnerModel;

import java.util.List;

public class PickupSpinnerResponse {
    private String status;
    private List<PickupSpinnerModel> list;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PickupSpinnerModel> getList() {
        return list;
    }

    public void setList(List<PickupSpinnerModel> list) {
        this.list = list;
    }
}
