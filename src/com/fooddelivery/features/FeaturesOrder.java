package com.fooddelivery.features;

import java.util.HashMap;

import com.fooddelivery.model.Customer;
import com.fooddelivery.model.FoodItem;

public class FeaturesOrder {

    private int orderId;
    private Customer customer;
    private HashMap<FoodItem,Integer> totalAmount;
    private String status;

    public FeaturesOrder(int orderId, Customer customer, HashMap<FoodItem, Integer> hashMap) {
        this.orderId = orderId;
        this.customer = customer;
        this.totalAmount = hashMap;
        this.status = "PLACED";
    }

    public void Order(int orderId2, Customer loggedInCustomer, Object totalAmount2) {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public HashMap<FoodItem,Integer> getTotalAmount() {
        return totalAmount;
    }

	public Object getDeliveryPerson() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDeliveryPerson(DeliveryPerson dp) {
		// TODO Auto-generated method stub
		
	}
}