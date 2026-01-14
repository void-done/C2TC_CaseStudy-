package com.fooddelivery.model;
import java.util.Map;
import java.util.HashMap;

public class Cart {
    private Map<FoodItem, Integer> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(FoodItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(FoodItem item) {
        items.remove(item);
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Cart Items:");
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey() + ", Quantity: " + entry.getValue());
        }
    }

    public Map<FoodItem, Integer> getItems() { return items; }
    public double getTotal() {
        double total = 0;
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public void clear() { items.clear(); }
}