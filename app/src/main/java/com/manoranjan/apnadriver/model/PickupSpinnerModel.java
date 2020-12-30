package com.manoranjan.apnadriver.model;

public class PickupSpinnerModel {
    String id,pickupslot,dropup;


    public PickupSpinnerModel() {
    }

    public PickupSpinnerModel(String id, String pickupslot) {
        this.id = id;
        this.pickupslot = pickupslot;
    }

    public String getDropup() {
        return dropup;
    }

    public void setDropup(String dropup) {
        this.dropup = dropup;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPickupslot() {
        return pickupslot;
    }

    public void setPickupslot(String pickupslot) {
        this.pickupslot = pickupslot;
    }
}

