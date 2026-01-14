package com.fooddelivery.features;
import com.fooddelivery.model.*;
import com.fooddelivery.service.*;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<FoodItem, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    // Add item with quantity
    public void addItem(FoodItem item, int quantity) {
        if (quantity <= 0) {
            quantity = 1; // default quantity
        }
        items.put(item, quantity);
    }

    // Calculate total price
    public double getTotalPrice() {
        double total = 0;

        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}