package com.fooddelivery.model;
import java.util.List;
import java.util.ArrayList;

public class Restaurant {
    private int id;
    private String name;
    private List<FoodItem> menu;

    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
        this.menu = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<FoodItem> getMenu() { return menu; }

    public void addFoodItem(FoodItem item) { menu.add(item); }
    public void removeFoodItem(FoodItem item) { menu.remove(item); }

    @Override
    public String toString() {
        return "Restaurant ID: " + id + ", Name: " + name;
    }
}