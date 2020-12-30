package com.manoranjan.apnadriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressModel {
    @SerializedName("house_type")
    @Expose
    private String houseType;
    @SerializedName("street_no")
    @Expose
    private String streetNo;
    @SerializedName("street_name")
    @Expose
    private String streetName;
    @SerializedName("post_code")
    @Expose
    private String postCode;
    @SerializedName("suburb")
    @Expose
    private String suburb;

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

}
