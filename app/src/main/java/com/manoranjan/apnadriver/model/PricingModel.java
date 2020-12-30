package com.manoranjan.apnadriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PricingModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("cartype_id")
    @Expose
    private String cartypeId;
    @SerializedName("hour")
    @Expose
    private String hour;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("company_price")
    @Expose
    private String companyPrice;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCartypeId() {
        return cartypeId;
    }

    public void setCartypeId(String cartypeId) {
        this.cartypeId = cartypeId;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCompanyPrice() {
        return companyPrice;
    }

    public void setCompanyPrice(String companyPrice) {
        this.companyPrice = companyPrice;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}

