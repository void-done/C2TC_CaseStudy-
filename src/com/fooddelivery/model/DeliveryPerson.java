package com.fooddelivery.model;
import java.util.List;
import java.util.ArrayList;

public class DeliveryPerson {
    private int id;
    private String name;
    private List<Order> orders;
    private boolean available;

    public DeliveryPerson(int id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new ArrayList<>();
        this.available = true;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<Order> getOrders() { return orders; }
    public boolean isAvailable() { return available; }

    public void assignOrder(Order order) {
        orders.add(order);
        available = false;
    }

    public void completeOrder(Order order) {
        orders.remove(order);
        available = true;
    }

    @Override
    public String toString() {
        return "Delivery Person ID: " + id + ", Name: " + name + ", Available: " + available;
    }

	public void setAvailable(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
