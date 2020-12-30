package com.manoranjan.apnadriver.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingListModel {
    @SerializedName("booking_id")
    @Expose
    private String bookingId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("reporting_address")
    @Expose
    private String reportingAddress;
    @SerializedName("nearest_landmark")
    @Expose
    private String nearestLandmark;
    @SerializedName("mobile_optional")
    @Expose
    private String mobileOptional;
    @SerializedName("booking_timing")
    @Expose
    private String bookingTiming;
    @SerializedName("desination_address")
    @Expose
    private String desinationAddress;
    @SerializedName("booking_hour")
    @Expose
    private String bookingHour;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("area_name")
    @Expose
    private String areaName;
    @SerializedName("cartype_name")
    @Expose
    private String cartypeName;

    @SerializedName("total_price")
    @Expose
    private String total_price;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("language")
    @Expose
    private String language;

    String driver_name,driver_mobile;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getReportingAddress() {
        return reportingAddress;
    }

    public void setReportingAddress(String reportingAddress) {
        this.reportingAddress = reportingAddress;
    }

    public String getNearestLandmark() {
        return nearestLandmark;
    }

    public void setNearestLandmark(String nearestLandmark) {
        this.nearestLandmark = nearestLandmark;
    }

    public String getMobileOptional() {
        return mobileOptional;
    }

    public void setMobileOptional(String mobileOptional) {
        this.mobileOptional = mobileOptional;
    }

    public String getBookingTiming() {
        return bookingTiming;
    }

    public void setBookingTiming(String bookingTiming) {
        this.bookingTiming = bookingTiming;
    }

    public String getDesinationAddress() {
        return desinationAddress;
    }

    public void setDesinationAddress(String desinationAddress) {
        this.desinationAddress = desinationAddress;
    }

    public String getBookingHour() {
        return bookingHour;
    }

    public void setBookingHour(String bookingHour) {
        this.bookingHour = bookingHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCartypeName() {
        return cartypeName;
    }

    public void setCartypeName(String cartypeName) {
        this.cartypeName = cartypeName;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getDriver_mobile() {
        return driver_mobile;
    }

    public void setDriver_mobile(String driver_mobile) {
        this.driver_mobile = driver_mobile;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}