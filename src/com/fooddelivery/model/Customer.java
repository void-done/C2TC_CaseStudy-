package com.fooddelivery.model;
import java.util.List;
import java.util.ArrayList;


import com.fooddelivery.features.FeaturesOrder;

import java.util.ArrayList;
import java.util.Collection;

public class Customer extends User {
    private String password;
    private Cart cart;
    private List<Order> orders;

    public Customer(int userId, String username, String contactNo, String password) {
        super(userId, username, contactNo);
        this.password = password;
        this.cart = new Cart();
        this.orders = new ArrayList<>();
    }

    public String getPassword() { return password; }
    public Cart getCart() { return cart; }
    public List<Order> getOrders() { return orders; }
    public class Custome {

        private List<Order> orders = new ArrayList<>();

        public void addOrder(Order order) {
            orders.add(order);   // ✅ FIXED LINE
        }

        public List<Order> getOrders() {
            return orders;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Password: " + password;
    }

	public void addOrder(Order order) {
		// TODO Auto-generated method stub
		
	}
}