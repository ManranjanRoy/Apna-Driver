package com.manoranjan.apnadriver.Response;

import com.manoranjan.apnadriver.model.AddressModel;

public class AddressResponse {
    String status;
    AddressModel data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AddressModel getData() {
        return data;
    }

    public void setData(AddressModel data) {
        this.data = data;
    }
}
