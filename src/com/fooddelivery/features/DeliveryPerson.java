package com.fooddelivery.features;

import com.fooddelivery.model.User;

public class DeliveryPerson extends User {

    private boolean available;

    public DeliveryPerson(int userId, String name, String phone) {
        super(userId, name, phone);
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}