package com.fooddelivery.model;
import java.util.Map;

public class Order {
    private int id;
    private Customer customer;
    private Map<FoodItem, Integer> items;
    private DeliveryPerson deliveryPerson;
    private String status;
    private double total;
    private PaymentMethod paymentMethod; // New field
    private PaymentStatus paymentStatus; // New field

    public Order(int id, Customer customer, Map<FoodItem, Integer> items) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.status = "Pending";
        this.total = calculateTotal();
        this.paymentStatus = PaymentStatus.PENDING; // Default
    }

    private double calculateTotal() {
        double sum = 0;
        for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum + 50; // Delivery charge
    }

    // Existing getters
    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Map<FoodItem, Integer> getItems() { return items; }
    public DeliveryPerson getDeliveryPerson() { return deliveryPerson; }
    public String getStatus() { return status; }
    public double getTotal() { return total; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }

    // Existing setters
    public void setDeliveryPerson(DeliveryPerson dp) { this.deliveryPerson = dp; }
    public void updateStatus(String status) { this.status = status; }

    // New setters
    public void setPaymentMethod(PaymentMethod method) { this.paymentMethod = method; }
    public void setPaymentStatus(PaymentStatus status) { this.paymentStatus = status; }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Customer: " + customer.getUsername() + ", Status: " + status + ", Payment: " + paymentMethod + " (" + paymentStatus + "), Total: ₹" + total;
    }
}