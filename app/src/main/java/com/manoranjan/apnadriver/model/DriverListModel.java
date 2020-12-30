package com.manoranjan.apnadriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverListModel {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("location_id")
    @Expose
    private String locationId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("adhar_card")
    @Expose
    private String adharCard;
    @SerializedName("voter_card")
    @Expose
    private String voterCard;
    @SerializedName("driving_licence")
    @Expose
    private String drivingLicence;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public boolean isSelected = false;
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAdharCard() {
        return adharCard;
    }

    public void setAdharCard(String adharCard) {
        this.adharCard = adharCard;
    }

    public String getVoterCard() {
        return voterCard;
    }

    public void setVoterCard(String voterCard) {
        this.voterCard = voterCard;
    }

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}