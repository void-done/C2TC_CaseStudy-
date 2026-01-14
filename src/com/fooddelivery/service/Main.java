package com.fooddelivery.service;
import com.fooddelivery.features.DeliveryService;

import java.util.*;
import com.fooddelivery.model.*;

public class Main {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static List<DeliveryPerson> deliveryPersons = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);
    private static int customerIdCounter = 1, restaurantIdCounter = 1, foodIdCounter = 1, orderIdCounter = 1, deliveryIdCounter = 1;
    private static Customer loggedInCustomer = null;

    public static void main(String[] args) {
        // Sample data
        restaurants.add(new Restaurant(restaurantIdCounter++, "Pizza Hut"));
        restaurants.get(0).addFoodItem(new FoodItem(foodIdCounter++, "Margherita Pizza", 200));
        deliveryPersons.add(new DeliveryPerson(deliveryIdCounter++, "John Doe"));

        while (true) {
            System.out.println("\n=== Food Delivery System ===");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login/Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            String input = sc.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1, 2, or 3).");
                continue;
            }

            switch (choice) {
                case 1: adminMenu(); break;
                case 2: customerMenu(); break;
                case 3: System.exit(0);
                default: System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            }
        }
    }

    private static void adminMenu() {
        System.out.print("Enter Admin Password: ");
        String pass = sc.nextLine();
        if (!pass.equals("admin123")) {
            System.out.println("Invalid password.");
            return;
        }
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item to Restaurant");
            System.out.println("3. Add Delivery Person");
            System.out.println("4. View Orders");
            System.out.println("5. Update Order Status");
            System.out.println("6. Back");
            System.out.print("Choose: ");
            String input = sc.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addRestaurant(); break;
                case 2: addFoodItem(); break;
                case 3: addDeliveryPerson(); break;
                case 4: viewOrders(); break;
                case 5: updateOrderStatus(); break;
                case 6: return;
                default: System.out.println("Invalid.");
            }
        }
    }

    private static void customerMenu() {
        while (true) {
            if (loggedInCustomer == null) {
                System.out.println("\n--- Customer Menu ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Back");
                System.out.print("Choose: ");
                String input = sc.nextLine().trim();
                int choice = -1;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1: registerCustomer(); break;
                    case 2: loginCustomer(); break;
                    case 3: return;
                    default: System.out.println("Invalid.");
                }
            } else {
                System.out.println("\n--- Logged in as " + loggedInCustomer.getUsername() + " ---");
                System.out.println("1. Browse Food");
                System.out.println("2. Add to Cart");
                System.out.println("3. View Cart");
                System.out.println("4. Place Order");
                System.out.println("5. View Orders");
                System.out.println("6. Cancel Order");
                System.out.println("7. Logout");
                System.out.print("Choose: ");
                String input = sc.nextLine().trim();
                int choice = -1;
                try {
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1: browseFood(); break;
                    case 2: addToCart(); break;
                    case 3: loggedInCustomer.getCart().viewCart(); break;
                    case 4: placeOrder(); break;
                    case 5: viewCustomerOrders(); break;
                    case 6: cancelOrder(); break;
                    case 7: loggedInCustomer = null; return;
                    default: System.out.println("Invalid.");
                }
            }
        }
    }

    // Helper methods for Admin
    private static void addRestaurant() {
        System.out.print("Restaurant Name: ");
        String name = sc.nextLine();
        restaurants.add(new Restaurant(restaurantIdCounter++, name));
        System.out.println("Added.");
    }

    private static void addFoodItem() {
        System.out.print("Restaurant ID: ");
        String input = sc.nextLine().trim();
        int rid = -1;
        try {
            rid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Restaurant ID.");
            return;
        }
        Restaurant r = findRestaurant(rid);
        if (r == null) return;
        System.out.print("Food Name: ");
        String name = sc.nextLine();
        System.out.print("Price: ");
        input = sc.nextLine().trim();
        double price = -1;
        try {
            price = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price.");
            return;
        }
        r.addFoodItem(new FoodItem(foodIdCounter++, name, price));
        System.out.println("Added.");
    }

    private static void addDeliveryPerson() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        deliveryPersons.add(new DeliveryPerson(deliveryIdCounter++, name));
        System.out.println("Added.");
    }

    private static void viewOrders() {
        for (Order o : orders) System.out.println(o);
    }

    private static void updateOrderStatus() {
        System.out.print("Order ID: ");
        String input = sc.nextLine().trim();
        int oid = -1;
        try {
            oid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID.");
            return;
        }
        Order o = findOrder(oid);
        if (o == null) return;
        System.out.print("New Status (Confirmed/Out for Delivery/Delivered): ");
        String status = sc.nextLine();
        o.updateStatus(status);
        if (status.equals("Delivered")) o.getDeliveryPerson().completeOrder(o);
        System.out.println("Updated.");
    }

    // Helper methods for Customer
    private static void registerCustomer() {
        System.out.print("Username: ");
        String uname = sc.nextLine();
        System.out.print("Contact: ");
        String contact = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        customers.add(new Customer(customerIdCounter++, uname, contact, pass));
        System.out.println("Registered. Your ID: " + (customerIdCounter - 1));
    }

    private static void loginCustomer() {
        System.out.print("Username: ");
        String uname = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();
        for (Customer c : customers) {
            if (c.getUsername().equals(uname) && c.getPassword().equals(pass)) {
                loggedInCustomer = c;
                System.out.println("Logged in.");
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private static void browseFood() {
        System.out.print("Search by name (or 'all'): ");
        String query = sc.nextLine();
        for (Restaurant r : restaurants) {
            System.out.println(r);
            for (FoodItem f : r.getMenu()) {
                if (query.equals("all") || f.getName().toLowerCase().contains(query.toLowerCase())) {
                    System.out.println("  " + f);
                }
            }
        }
    }

    private static void addToCart() {
        System.out.print("Food ID: ");
        String input = sc.nextLine().trim();
        int fid = -1;
        try {
            fid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Food ID.");
            return;
        }
        System.out.print("Quantity: ");
        input = sc.nextLine().trim();
        int qty = -1;
        try {
            qty = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }
        FoodItem item = findFoodItem(fid);
        if (item != null) {
            loggedInCustomer.getCart().addItem(item, qty);
            System.out.println("Added.");
        }
    }

    private static void placeOrder() {
        if (loggedInCustomer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        Order order = new Order(orderIdCounter++, loggedInCustomer, new HashMap<>(loggedInCustomer.getCart().getItems()));
        orders.add(order);
        loggedInCustomer.addOrder(order);
        generateBill(order);
        
        DeliveryService deliveryService = new DeliveryService();
        DeliveryPerson dp = deliveryService.assignDeliveryPerson(deliveryPersons);

        if (dp != null) {
            order.setDeliveryPerson(dp);
            order.updateStatus("Confirmed");
        }

        generateBill(order);
        // New: Select and process payment
        PaymentMethod method = selectPaymentMethod();
        order.setPaymentMethod(method);
        PaymentStatus paymentStatus=processPayment(method,order.getTotal());
        																																																																																													PaymentStatus paymentStatus1 = processPayment(method, order.getTotal());
        order.setPaymentStatus(paymentStatus1);
        if (paymentStatus1 == PaymentStatus.COMPLETED) {
            generateInvoice(order);
            loggedInCustomer.getCart().clear();
            System.out.println("Order placed successfully. ID: " + order.getId());
        } else {
            System.out.println("Payment failed. Order not placed.");
            orders.remove(order); // Remove failed order
            loggedInCustomer.getOrders().remove(order);
        }
    }

    private static void viewCustomerOrders() {
        if (loggedInCustomer.getOrders().isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        for (Order o : loggedInCustomer.getOrders()) {
            System.out.println(o);
        }
    }


    private static void cancelOrder() {
        System.out.print("Order ID: ");
        String input = sc.nextLine().trim();
        int oid = -1;
        try {
            oid = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Order ID.");
            return;
        }
        Order o = findOrder(oid);
        if (o != null && o.getCustomer().equals(loggedInCustomer) && !o.getStatus().equals("Out for Delivery")) {
            o.updateStatus("Cancelled");
            System.out.println("Cancelled.");
        } else {
            System.out.println("Cannot cancel.");
        }
    }

    // Utility methods
    private static Restaurant findRestaurant(int id) {
        for (Restaurant r : restaurants) if (r.getId() == id) return r;
        System.out.println("Restaurant not found.");
        return null;
    }

    private static FoodItem findFoodItem(int id) {
        for (Restaurant r : restaurants) {
            for (FoodItem f : r.getMenu()) if (f.getId() == id) return f;
        }
        System.out.println("Food item not found.");
        return null;
    }

    private static Order findOrder(int id) {
        for (Order o : orders) if (o.getId() == id) return o;
        System.out.println("Order not found.");
        return null;
    }

    private static void assignDelivery(Order order) {
        for (DeliveryPerson dp : deliveryPersons) {
            if (dp.isAvailable()) {
                dp.assignOrder(order);
                order.setDeliveryPerson(dp);
                order.updateStatus("Confirmed");
                return;
            }
        }
        System.out.println("No delivery person available.");
    }

    private static void generateBill(Order order) {
        double subtotal = order.getTotal() - 50;
        double gst = subtotal * 0.18;
        double total = subtotal + gst + 50;
        System.out.println("\n--- Bill ---");
        System.out.println("Subtotal: ₹" + subtotal);
        System.out.println("GST (18%): ₹" + gst);
        System.out.println("Delivery: ₹50");
        System.out.println("Total: ₹" + total);
    }

    // New: Payment-related methods
    private static PaymentMethod selectPaymentMethod() {
        while (true) {
            System.out.println("\n--- Select Payment Method ---");
            System.out.println("1. UPI");
            System.out.println("2. Card");
            System.out.println("3. Cash on Delivery (COD)");
            System.out.print("Choose: ");
            String input = sc.nextLine().trim();
            int choice = -1;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            switch (choice) {
                case 1: return PaymentMethod.UPI;
                case 2: return PaymentMethod.CARD;
                case 3: return PaymentMethod.COD;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    private static PaymentStatus processPayment(PaymentMethod method, double amount) {
        if (method == PaymentMethod.COD) {
            System.out.println("COD selected. Payment will be collected on delivery.");
            return PaymentStatus.COMPLETED;
        }
        System.out.println("Processing payment of ₹" + amount + " via " + method + "...");
        if (method == PaymentMethod.UPI) {
            System.out.print("Enter UPI ID: ");
            String upiId = sc.nextLine();
            // Simulate success/failure (e.g., random for demo)
            return Math.random() > 0.1 ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
        } else if (method == PaymentMethod.CARD) {
            System.out.print("Enter Card Number: ");
            String cardNum = sc.nextLine();
            System.out.print("Enter Expiry (MM/YY): ");
            String expiry = sc.nextLine();
            System.out.print("Enter CVV: ");
            String cvv = sc.nextLine();
            // Simulate success/failure
            return Math.random() > 0.1 ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
        }
        return PaymentStatus.FAILED;
    }

    private static void generateInvoice(Order order) {
        System.out.println("\n--- Invoice ---");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomer().getUsername());
        System.out.println("Items:");
        for (Map.Entry<FoodItem, Integer> entry : order.getItems().entrySet()) {
            System.out.println("  " + entry.getKey().getName() + " x" + entry.getValue() + " = ₹" + (entry.getKey().getPrice() * entry.getValue()));
        }
        double subtotal = order.getTotal() - 50;
        double gst = subtotal * 0.18;
        System.out.println("Subtotal: ₹" + subtotal);
        System.out.println("GST (18%): ₹" + gst);
        System.out.println("Delivery: ₹50");
        System.out.println("Total: ₹" + order.getTotal());
        System.out.println("Payment Method: " + order.getPaymentMethod());
        System.out.println("Payment Status: " + order.getPaymentStatus());
        System.out.println("Order Status: " + order.getStatus());
        System.out.println("Thank you for your order!");
    }
}